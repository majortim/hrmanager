package kr.co.hrmanager.service.leaves;

import kr.co.hrmanager.web.dto.leaves.LeavesCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class LeavesServiceTest {

    @Autowired
    LeavesService leavesService;

    @Test
    @Sql({
            "/sql/data/employees.sql"
            , "/sql/data/leaves.sql"
            , "/sql/data/tna.sql"
    })
    void create() {
        LeavesCreateRequest request = LeavesCreateRequest.builder()
                .empId(1L)
                .baseYear(2023)
                .build();

        leavesService.create(request);
    }

    private void setupCreateData() {

    }
}