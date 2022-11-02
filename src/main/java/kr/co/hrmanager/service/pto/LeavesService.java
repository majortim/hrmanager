package kr.co.hrmanager.service.pto;


import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.leaves.Leaves;
import kr.co.hrmanager.domain.leaves.LeavesRepository;
import kr.co.hrmanager.web.dto.pto.LeavesCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LeavesService {
    private final LeavesRepository repository;
    private final EmployeesRepository employeesRepository;

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

            LocalDate targetDate = LocalDate.of(baseYear, hireDt.getMonth(), hireDt.getDayOfMonth());
            LocalDate prevYearDate = targetDate.minusYears(1);



        });

        return null; //repository.save(param);
    }
}
