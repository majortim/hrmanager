package kr.co.hrmanager.service.employees;

import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeesService {
    private final EmployeesRepository repository;

    public Optional<Employees> findById(Long id) {
        return repository.findById(id);
    }

    public List<Employees> findByAll(Example<Employees> queryParam) {
        return repository.findAll(queryParam);
    }

    @Transactional
    public Employees save(Employees employee) {
        return repository.save(employee);
   }
}
