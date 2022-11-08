package kr.co.hrmanager.service.salaries;

import kr.co.hrmanager.domain.salaries.AnnualBasicSalaries;
import kr.co.hrmanager.domain.salaries.AnnualBasicSalariesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AnnualBasicSalariesService {
    private final AnnualBasicSalariesRepository repository;

    public Optional<AnnualBasicSalaries> findById(Long id) {
        return repository.findById(id);
    }

    public List<AnnualBasicSalaries> findByAll(Example<AnnualBasicSalaries> queryParam) {
        return repository.findAll(queryParam);
    }
}
