package kr.co.hrmanager.service.salaries;

import kr.co.hrmanager.domain.salaries.SalaryParticulars;
import kr.co.hrmanager.domain.salaries.SalaryParticularsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SalarieyParticularsService {
    private final SalaryParticularsRepository repository;

    public Optional<SalaryParticulars> findById(Long id) {
        return repository.findById(id);
    }

    public List<SalaryParticulars> findByAll(Example<SalaryParticulars> queryParam) {
        return repository.findAll(queryParam);
    }
}
