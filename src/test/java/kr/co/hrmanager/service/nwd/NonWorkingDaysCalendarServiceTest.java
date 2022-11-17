package kr.co.hrmanager.service.nwd;

import kr.co.hrmanager.domain.nwd.NonWorkingDaysCalendar;
import kr.co.hrmanager.web.dto.nwd.CalendarCreateRequest;
import kr.co.hrmanager.web.dto.nwd.CalendarFindRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NonWorkingDaysCalendarServiceTest {
    @Autowired
    NonWorkingDaysCalendarService nonWorkingDaysCalendarService;

    @Test
    @Sql({
            "/sql/data/nwd_annual.sql"
            , "/sql/data/nwd_weekly.sql"
    })
    void create() {
        CalendarCreateRequest request = CalendarCreateRequest.of(2022);

        boolean created = nonWorkingDaysCalendarService.create(request);

        assertTrue(created);
    }

    @Test
    @Sql({
            "/sql/data/nwd_annual.sql"
            , "/sql/data/nwd_weekly.sql"
    })
    @Transactional
    public void createAndFind() {
        CalendarCreateRequest createRequest = CalendarCreateRequest.of(2022);
        CalendarFindRequest findRequest = CalendarFindRequest.of(2022);

        nonWorkingDaysCalendarService.create(createRequest);
        List<NonWorkingDaysCalendar> list = nonWorkingDaysCalendarService.findAllByYear(findRequest);

        log.debug("found size: {}", list.isEmpty() ? 0 : list.size());
        assertFalse(list.isEmpty());
        assertNotEquals(0, list.size());
    }
}