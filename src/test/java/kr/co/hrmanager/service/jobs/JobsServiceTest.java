package kr.co.hrmanager.service.jobs;

import kr.co.hrmanager.web.dto.jobs.JobsSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class JobsServiceTest {

    @Autowired
    JobsService jobsService;

    @Test
    void save() {
        JobsSaveRequest request = JobsSaveRequest.builder()
                .jobId(1L)
                .jobTitle("개발자")
                .build();

        Long savedId = jobsService.save(request);

        assertEquals(1L, savedId);
    }
}