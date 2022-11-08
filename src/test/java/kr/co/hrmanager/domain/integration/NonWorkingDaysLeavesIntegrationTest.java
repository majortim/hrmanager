package kr.co.hrmanager.domain.integration;

import kr.co.hrmanager.service.leaves.LeavesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NonWorkingDaysLeavesIntegrationTest {
    @Autowired
    LeavesService leavesService;

    @Test
    void createNonWorkingDaysAndLeaves() {

    }
}
