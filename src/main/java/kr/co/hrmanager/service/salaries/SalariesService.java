package kr.co.hrmanager.service.salaries;

import kr.co.hrmanager.domain.salaries.Salaries;
import kr.co.hrmanager.domain.salaries.SalariesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SalariesService {
    private final SalariesRepository repository;

    public Optional<Salaries> findById(Long id) {
        return repository.findById(id);
    }

    public List<Salaries> findByAll(Example<Salaries> queryParam) {
        return repository.findAll(queryParam);
    }
}
