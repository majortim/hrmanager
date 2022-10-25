package kr.co.hrmanager.service.employees;

import kr.co.hrmanager.domain.employees.EmployeeStatus;
import kr.co.hrmanager.domain.employees.EmployeeStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeStatusService {
    private final EmployeeStatusRepository repository;

    public Optional<EmployeeStatus> findById(Long id) {
        return repository.findById(id);
    }

    public List<EmployeeStatus> findByAll(Example<EmployeeStatus> queryParam) {
        return repository.findAll(queryParam);
    }
}
