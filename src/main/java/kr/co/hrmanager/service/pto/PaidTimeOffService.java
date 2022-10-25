package kr.co.hrmanager.service.pto;


import kr.co.hrmanager.domain.pto.PaidTimeOff;
import kr.co.hrmanager.domain.pto.PaidTimeOffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PaidTimeOffService {
    private final PaidTimeOffRepository repository;

    public Optional<PaidTimeOff> findById(Long id) {
        return repository.findById(id);
    }
    public List<PaidTimeOff> findAll(Example<PaidTimeOff> param) {
        return repository.findAll(param);
    }

    @Transactional
    public PaidTimeOff save(PaidTimeOff param) {
        return repository.save(param);
    }
}
