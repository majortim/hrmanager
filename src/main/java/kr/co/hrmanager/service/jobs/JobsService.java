package kr.co.hrmanager.service.jobs;

import kr.co.hrmanager.domain.jobs.Jobs;
import kr.co.hrmanager.domain.jobs.JobsRepository;
import kr.co.hrmanager.dto.jobs.JobsSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JobsService {
    private final JobsRepository jobsRepository;

    public Optional<Jobs> findById(Long id) {
        return jobsRepository.findById(id);
    }

    @Transactional
    public Long save(JobsSaveRequest requestDto) {
        return jobsRepository.save(requestDto.toEntity()).getJobId();
    }
}
