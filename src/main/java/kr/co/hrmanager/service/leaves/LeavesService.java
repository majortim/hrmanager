package kr.co.hrmanager.service.leaves;

import kr.co.hrmanager.domain.employees.EmployeeStatusRepository;
import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.leaves.LeaveType;
import kr.co.hrmanager.domain.leaves.Leaves;
import kr.co.hrmanager.domain.leaves.LeavesRepository;
import kr.co.hrmanager.domain.nwd.NonWorkingDaysCalendarRepository;
import kr.co.hrmanager.domain.tna.Tna;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.domain.tna.TnaType;
import kr.co.hrmanager.web.dto.leaves.LeavesCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final LeavesRepository repository;
    private final EmployeesRepository employeesRepository;
    private final EmployeeStatusRepository employeeStatusRepository;
    private final NonWorkingDaysCalendarRepository nonWorkingDaysCalendarRepository;
    private final TnaRepository tnaRepository;

    public Optional<Leaves> findById(Long id) {
        return repository.findById(id);
    }
    /*
    public List<Leaves> findAll(Example<Leaves> param) {
        return repository.findAll(param);
    }*/

    @Transactional
    public Leaves create(LeavesCreateRequest createRequest) {
        Optional<Employees> optionalEmployee = Optional.ofNullable(createRequest.getEmpId())
                .flatMap(employeesRepository::findById);

        optionalEmployee.ifPresent(employee -> {
            Integer baseYear = Optional.of(createRequest.getBaseYear()).orElseThrow(() -> new NoSuchElementException("기준연도가 지정되지 않았습니다."));
            LocalDateTime hireDt = Optional.of(employee.getHireDt()).orElseThrow(() -> new NoSuchElementException("채용일시가 지정되지 않았습니다."));

            LocalDateTime prevYearDt = LocalDateTime.of(baseYear, hireDt.getMonth(), hireDt.getDayOfMonth(), 0, 0).minusYears(1);
            LocalDateTime targetDt = prevYearDt.plusYears(1).plusDays(1).minusNanos(1);

            long daysBetweenDates = DAYS.between(prevYearDt, targetDt);
            long nonWorkingDays = nonWorkingDaysCalendarRepository.countByEnabledAndNwdDtBetween(true, prevYearDt, targetDt);

            long prescribedWorkingDays = daysBetweenDates - nonWorkingDays;

            long countAbsence;  //휴가, 휴직, 결근
            //무단결근
            long countWithoutLeave
                    = tnaRepository.countByEmployeeAndTnaTypeListAndDateTime(employee, List.of(TnaType.ABSENCE_WITHOUT_LEAVE), prevYearDt, targetDt);

            Stream<Tna> streamAbsence
                    = tnaRepository.steamByEmployeeAndTnaTypeListAndDateTime(employee, List.of(TnaType.LEAVE_OF_ABSENCE), prevYearDt, targetDt);
            //개인 사유로 인한 휴직
            long countPersonal = streamAbsence.filter(
                    t -> Optional.ofNullable(t.getLeave())
                    .map(Leaves::getLeaveTy)
                    .stream()
                    .anyMatch(leaveType -> leaveType.equals(LeaveType.PERSONAL)))
            .count();

            countAbsence = countWithoutLeave + countPersonal;

            log.debug("countAbsence: {}", countAbsence);

            double workRatio = ((double) (prescribedWorkingDays - countAbsence)) / ((double) prescribedWorkingDays) * 100.0;

            log.debug("workRatio: {}", workRatio);

            if(workRatio >= 80.0) {
                //TODO
                log.debug("80% 이상");
            }
            else {
                //TODO
                log.debug("80% 미만");
            }
        });

        return null; //repository.save(param);
    }
}
