package kr.co.hrmanager.service.jobs;

import kr.co.hrmanager.domain.jobs.Jobs;
import kr.co.hrmanager.dto.jobs.JobsSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class JobsServiceTest {

    @Autowired
    JobsService jobsService;

    @Test
    @Transactional
    void save() {

        Long savedId = saveJob();

        assertEquals(1L, savedId);
    }

    @Test
    @Transactional
    void saveAndUpdate() {
        Long savedId = saveJob();

        Optional<Jobs> foundJob = jobsService.findById(savedId);

        foundJob.ifPresent(j -> j.updateJobs("기획자"));
        foundJob = jobsService.findById(savedId);
        foundJob.ifPresent(j-> assertEquals("기획자", j.getJobTitle()));


    }

    @Transactional
    Long saveJob() {
        JobsSaveRequest request = JobsSaveRequest.builder()
                .jobId(1L)
                .jobTitle("개발자")
                .build();

        return jobsService.save(request);
    }
}