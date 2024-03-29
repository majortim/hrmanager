package kr.co.hrmanager.service.leaves;

import kr.co.hrmanager.domain.employees.*;
import kr.co.hrmanager.domain.leaves.LeaveType;
import kr.co.hrmanager.domain.leaves.Leaves;
import kr.co.hrmanager.domain.leaves.LeavesRepository;
import kr.co.hrmanager.domain.nwd.NonWorkingDaysCalendarRepository;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.domain.tna.TnaType;
import kr.co.hrmanager.domain.users.Users;
import kr.co.hrmanager.dto.leaves.CreateAnnualRequest;
import kr.co.hrmanager.dto.nwd.CalendarDateResponse;
import kr.co.hrmanager.dto.tna.FindTnaCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LeavesService {
    private static final long DEFAULT_ANNUAL_LEAVES = 15;
    private final LeavesRepository leavesRepository;
    private final EmployeesRepository employeesRepository;
    private final EmployeeStatusRepository employeeStatusRepository;
    private final NonWorkingDaysCalendarRepository nonWorkingDaysCalendarRepository;
    private final TnaRepository tnaRepository;

    public Optional<Leaves> findById(Long id) {
        return leavesRepository.findById(id);
    }

    @Transactional
    public Leaves createAnnual(CreateAnnualRequest createRequest) {
        String username = Optional.of(createRequest.getUsername()).orElseThrow();
        Users user = Users.builder().username(username).build();
        Employees employee = employeesRepository.findByUser(user).orElseThrow();

        Integer baseYear = Optional.of(createRequest.getBaseYear()).orElseThrow(() -> new NoSuchElementException("기준연도가 지정되지 않았습니다."));
        LocalDateTime hireDt = Optional.of(employee.getHireDt()).orElseThrow(() -> new NoSuchElementException("채용일시가 지정되지 않았습니다."));

        final LocalDateTime targetStartDt = LocalDateTime.of(baseYear, hireDt.getMonth(), hireDt.getDayOfMonth(), 0, 0).minusYears(1);
        final LocalDateTime targetEndDt = targetStartDt.plusYears(1).plusDays(1).minusNanos(1);
        final LocalDate targetStartDate = targetStartDt.toLocalDate();
        final LocalDate targetEndDate = targetEndDt.toLocalDate();

        //기준일 카운트, 기본 365일
        long daysBetweenDates = DAYS.between(targetStartDt, targetEndDt);
        //휴일, 휴무일
        List<LocalDate> nonWorkingDaysList =
                nonWorkingDaysCalendarRepository.findNwdDateByEnabledAndNwdDateBetween(true, targetStartDt.toLocalDate(), targetEndDt.toLocalDate())
                .stream().map(CalendarDateResponse::getNwdDate).collect(Collectors.toList());
        long nonWorkingDays = nonWorkingDaysList.size();
        log.debug("nonWorkingDaysList: {}", nonWorkingDaysList);
        log.debug("nonWorkingDays: {}", nonWorkingDays);

        //소정근로일
        long prescribedWorkingDays = daysBetweenDates - nonWorkingDays;
        //연차 계산할 때 출근한 것으로 계산하지 않는 휴가/휴직, 결근, 정직
        log.debug("prescribedWorkingDays: {}", prescribedWorkingDays);
        long countAbsence;
        //무단결근
        FindTnaCondition conditionWithoutLeave
                = FindTnaCondition.builder()
                .username(username)
                .tnaType(TnaType.ABSENCE_WITHOUT_LEAVE)
                .targetStartDt(targetStartDt)
                .targetEndDt(targetEndDt)
                .build();

        Set<LocalDate> awlLocalDateSet =  tnaRepository.streamByCondition(conditionWithoutLeave)
                .flatMap(tna -> tna.toSetAllDates(targetStartDate, targetEndDate).stream()).collect(Collectors.toSet());
        nonWorkingDaysList.forEach(awlLocalDateSet::remove);
        long countWithoutLeave = awlLocalDateSet.size();

        //개인 사유로 인한 휴직
        FindTnaCondition conditionPersonal
                = FindTnaCondition.builder()
                .username(username)
                .tnaType(TnaType.LEAVE_OF_ABSENCE)
                .leaveType(LeaveType.PERSONAL)
                .targetStartDt(targetStartDt)
                .targetEndDt(targetEndDt)
                .build();

        Set<LocalDate> personalLocalDateSet =  tnaRepository.streamByCondition(conditionPersonal)
                .flatMap(tna -> tna.toSetAllDates(targetStartDate, targetEndDate).stream()).collect(Collectors.toSet());
        nonWorkingDaysList.forEach(personalLocalDateSet::remove);
        long countPersonal = personalLocalDateSet.size();

        //정직
        Stream<EmployeeStatus> streamSuspended
                = employeeStatusRepository.findAllByEnabledAndDates(true, targetStartDt.toLocalDate(), targetEndDt.toLocalDate())
                .filter(status -> status.getEsTy().equals(EmployeeStatusType.SUSPENDED));
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
                    .baseYear(baseYear)
                    .leaveTy(LeaveType.ANNUAL)
                    .leaveCnt(DEFAULT_ANNUAL_LEAVES + extraDays)
                    .paid(true)
                    .markedAsWorked(true)
                    .enabled(true)
                    .build();

            return leavesRepository.save(leave);
        }
        else {
            log.debug("80% 미만");
            Set<LocalDate> prescriedWorkingDaysSet = targetStartDate.datesUntil(targetEndDate.plusDays(1))
                    .collect(Collectors.toSet());
            nonWorkingDaysList.forEach(prescriedWorkingDaysSet::remove);
        }

        return null;
    }

    @Transactional
    public void deleteById(Long id) {
        leavesRepository.deleteById(id);
    }
}
