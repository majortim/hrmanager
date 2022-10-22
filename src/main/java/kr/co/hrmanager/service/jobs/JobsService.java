package kr.co.hrmanager.service.jobs;

import kr.co.hrmanager.domain.jobs.JobsRepository;
import kr.co.hrmanager.web.dto.jobs.JobsSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class JobsService {
    private final JobsRepository jobsRepository;

    @Transactional
    public Long save(JobsSaveRequest requestDto) {
        return jobsRepository.save(requestDto.toEntity()).getJobId();
    }
}
