package kr.co.hrmanager.service.tna;

import kr.co.hrmanager.domain.tna.Tna;
import kr.co.hrmanager.domain.tna.TnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TnaService {
    private final TnaRepository repository;

    public Optional<Tna> findById(Long id) {
        return repository.findById(id);
    }

    public List<Tna> findByAll(Example<Tna> queryParam) {
        return repository.findAll(queryParam);
    }
}
