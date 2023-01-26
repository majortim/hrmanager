package kr.co.hrmanager.service.integration;

import kr.co.hrmanager.dto.leaves.CreateAnnualRequest;
import kr.co.hrmanager.dto.nwd.CalendarCreateRequest;
import kr.co.hrmanager.service.leaves.LeavesService;
import kr.co.hrmanager.service.nwd.NonWorkingDaysCalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class NonWorkingDaysLeavesServiceTest {
    @Autowired
    NonWorkingDaysCalendarService nonWorkingDaysCalendarService;
    @Autowired
    LeavesService leavesService;

    @Test
    @Transactional
    @Sql({
            "/sql/data/employees.sql"
            , "/sql/data/nwd_annual.sql"
            , "/sql/data/nwd_weekly.sql"
            , "/sql/data/leaves.sql"
            , "/sql/data/tna.sql"
    })
    void createNonWorkingDaysAndLeaves() {
        setupCreateData(CalendarCreateRequest.of(2021));
        setupCreateData(CalendarCreateRequest.of(2022));
        CreateAnnualRequest request = CreateAnnualRequest.builder()
                .username("admin")
                .baseYear(2022)
                .build();
        leavesService.createAnnual(request);
    }

    @Transactional
    void setupCreateData(CalendarCreateRequest request) {
        nonWorkingDaysCalendarService.create(request);
    }
}
