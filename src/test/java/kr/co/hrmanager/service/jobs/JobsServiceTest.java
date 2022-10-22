package kr.co.hrmanager.service.jobs;

import kr.co.hrmanager.domain.jobs.Jobs;
import kr.co.hrmanager.web.dto.jobs.JobsSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class JobsServiceTest {

    @Autowired
    JobsService jobsService;

    @Test
    void save() {

        Long savedId = saveCommon();

        assertEquals(1L, savedId);
    }

    @Transactional
    @Test
    void saveAndUpdate() {
        Long savedId = saveCommon();

        Optional<Jobs> foundJob = jobsService.findById(savedId);

        foundJob.ifPresent(j -> j.updateJobs("기획자"));
        foundJob = jobsService.findById(savedId);
        foundJob.ifPresent(j-> assertEquals("기획자", j.getJobTitle()));


    }

    private Long saveCommon() {
        JobsSaveRequest request = JobsSaveRequest.builder()
                .jobId(1L)
                .jobTitle("개발자")
                .build();

        return jobsService.save(request);
    }
}