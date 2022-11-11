package kr.co.hrmanager.service.leaves;

import kr.co.hrmanager.domain.employees.*;
import kr.co.hrmanager.domain.leaves.LeaveType;
import kr.co.hrmanager.domain.leaves.Leaves;
import kr.co.hrmanager.domain.leaves.LeavesRepository;
import kr.co.hrmanager.domain.nwd.NonWorkingDaysCalendarRepository;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.domain.tna.TnaType;
import kr.co.hrmanager.web.dto.leaves.CreateAnnualRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LeavesService {
    private static final long DEFAULT_ANNUAL_LEAVES = 15;
    private final LeavesRepository repository;
    private final EmployeesRepository employeesRepository;
    private final EmployeeStatusRepository employeeStatusRepository;
    private final NonWorkingDaysCalendarRepository nonWorkingDaysCalendarRepository;
    private final TnaRepository tnaRepository;

    public Optional<Leaves> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Leaves createAnnual(CreateAnnualRequest createRequest) {
        Employees employee = Optional.ofNullable(createRequest.getEmpId())
                .flatMap(employeesRepository::findById).orElseThrow();

        Integer baseYear = Optional.of(createRequest.getBaseYear()).orElseThrow(() -> new NoSuchElementException("기준연도가 지정되지 않았습니다."));
        LocalDateTime hireDt = Optional.of(employee.getHireDt()).orElseThrow(() -> new NoSuchElementException("채용일시가 지정되지 않았습니다."));

        LocalDateTime targetStartDt = LocalDateTime.of(baseYear, hireDt.getMonth(), hireDt.getDayOfMonth(), 0, 0).minusYears(1);
        LocalDateTime targetEndDt = targetStartDt.plusYears(1).plusDays(1).minusNanos(1);

        //기준일 카운트, 기본 365일
        long daysBetweenDates = DAYS.between(targetStartDt, targetEndDt);
        //휴일, 휴무일
        long nonWorkingDays = nonWorkingDaysCalendarRepository.countByEnabledAndNwdDateBetween(true, targetStartDt.toLocalDate(), targetEndDt.toLocalDate());
        //소정근로일
        long prescribedWorkingDays = daysBetweenDates - nonWorkingDays;
        //연차 계산할 때 출근한 것으로 계산하지 않는 휴가/휴직, 결근, 정직
        long countAbsence;
        //무단결근
        long countWithoutLeave
                = tnaRepository.countByEmployeeAndTnaTypeListAndDateTime(employee, List.of(TnaType.ABSENCE_WITHOUT_LEAVE), targetStartDt, targetEndDt);
        //개인 사유로 인한 휴직

        //
        /*
        Stream<Tna> streamAbsence
                = tnaRepository.steamByEmployeeAndTnaTypeListAndDateTime(employee, List.of(TnaType.LEAVE_OF_ABSENCE), targetStartDt, targetEndDt);

        // streamAbsence.filter(
        //                    t -> Optional.ofNullable(t.getLeave())
        //                    .map(Leaves::getLeaveTy)
        //                    .stream()
        //                    .anyMatch(leaveType -> leaveType.equals(LeaveType.PERSONAL)))
        //            .count()*/
        long countPersonal = 0;

        Stream<EmployeeStatus> streamSuspended
                = employeeStatusRepository.findAllByEnabledAndDates(true, targetStartDt.toLocalDate(), targetEndDt.toLocalDate())
                .filter(status -> status.getEsTy().equals(EmployeeStatusType.SUSPENDED));
        //정직
        long countSuspended = streamSuspended.count();

        countAbsence = countWithoutLeave + countPersonal + countSuspended;
        log.debug("countAbsence: {}", countAbsence);

        double workRatio = ((double) (prescribedWorkingDays - countAbsence)) / ((double) prescribedWorkingDays) * 100.0;

        log.debug("workRatio: {}", workRatio);

        Period period = Period.between(hireDt.toLocalDate(), targetEndDt.toLocalDate());
        long years = period.getYears();

        //소정근로일의 80% 이상 출근, 근속 1년 이상
        if(workRatio >= 80.0 && years >= 1) {
            log.debug("80% 이상");
            long extraDays = 0;

            if(years >= 3) {
                extraDays = (years - 1) / 2;
            }

            Leaves leave = Leaves.builder()
                    .employee(employee)
                    .leaveTy(LeaveType.ANNUAL)
                    .leaveCnt(DEFAULT_ANNUAL_LEAVES + extraDays)
                    .paid(true)
                    .markedAsWorked(true)
                    .createDt(LocalDateTime.now())
                    .build();

            return repository.save(leave);
        }
        else {
            //TODO
            log.debug("80% 미만");
        }

        return null;
    }
}
