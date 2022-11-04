package kr.co.hrmanager.service.leaves;

import kr.co.hrmanager.domain.employees.EmployeeStatusRepository;
import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.leaves.Leaves;
import kr.co.hrmanager.domain.leaves.LeavesRepository;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.domain.tna.TnaType;
import kr.co.hrmanager.web.dto.leaves.LeavesCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class LeavesService {
    private final LeavesRepository repository;
    private final EmployeesRepository employeesRepository;
    private final EmployeeStatusRepository employeeStatusRepository;
    private final TnaRepository tnaRepository;
    private final LeavesRepository leavesRepository;

    public Optional<Leaves> findById(Long id) {
        return repository.findById(id);
    }
    public List<Leaves> findAll(Example<Leaves> param) {
        return repository.findAll(param);
    }

    @Transactional
    public Leaves create(LeavesCreateRequest createRequest) {
        Optional<Employees> optionalEmployee = Optional.ofNullable(createRequest.getEmpId())
                .flatMap(employeesRepository::findById);

        optionalEmployee.ifPresent(e -> {
            Integer baseYear = Optional.of(createRequest.getBaseYear()).orElseThrow(() -> new NoSuchElementException("기준연도가 지정되지 않았습니다."));
            LocalDateTime hireDt = Optional.of(e.getHireDt()).orElseThrow(() -> new NoSuchElementException("채용일시가 지정되지 않았습니다."));

            LocalDateTime prevYearDt = LocalDateTime.of(baseYear, hireDt.getMonth(), hireDt.getDayOfMonth(), 0, 0).minusYears(1);
            LocalDateTime targetDt = prevYearDt.plusYears(1).plusDays(1).minusNanos(1);

            long countAwl = tnaRepository.countByTnaTypeAndDateTime(TnaType.ABSENCE_WITHOUT_LEAVE, prevYearDt, targetDt);

            log.debug("count between dates: {}", DAYS.between(prevYearDt, targetDt));
            log.debug("countAwl: {}", countAwl);
        });

        return null; //repository.save(param);
    }
}
