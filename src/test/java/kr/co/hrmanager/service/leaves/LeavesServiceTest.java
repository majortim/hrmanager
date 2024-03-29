package kr.co.hrmanager.service.leaves;

import kr.co.hrmanager.domain.leaves.Leaves;
import kr.co.hrmanager.dto.leaves.CreateAnnualRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class LeavesServiceTest {

    @Autowired
    LeavesService leavesService;

    @Test
    @Transactional
    @Sql({
            "/sql/data/employees.sql"
            , "/sql/data/leaves.sql"
            , "/sql/data/tna.sql"
    })
    void create() {
        CreateAnnualRequest request = CreateAnnualRequest.builder()
                .username("admin")
                .baseYear(2023)
                .build();

        Leaves createdLeave = leavesService.createAnnual(request);

        assertNotNull(createdLeave);
    }
}