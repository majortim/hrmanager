package kr.co.hrmanager.domain.nwd;

import kr.co.hrmanager.dto.nwd.CalendarDateResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Slf4j
class NonWorkingDaysCalendarRepositoryTest {
    @Autowired
    NonWorkingDaysCalendarRepository repository;

    @Test
    @Transactional
    void countByEnabledAndNwdDateBetween() {
        Boolean enabled = true;
        LocalDateTime targetStartDt = LocalDateTime.of(2022, 3, 1, 1, 1);
        LocalDateTime targetEndDt = targetStartDt.plusYears(1).plusDays(1).minusNanos(1);

        long count = repository.countByEnabledAndNwdDateBetween(enabled, targetStartDt.toLocalDate(), targetEndDt.toLocalDate());

        assertEquals(0, count);
    }

    @Test
    @Transactional
    @Sql({
            "/sql/data/nwd_annual.sql",
            "/sql/data/nwd_calendar.sql"
    })
    void listNwdDateByEnabledAndNwdDateBetween() {
        Boolean enabled = true;
        LocalDateTime targetStartDt = LocalDateTime.of(2022, 2, 1, 1, 1);
        LocalDateTime targetEndDt = targetStartDt.plusYears(1).plusDays(1).minusNanos(1);

        List<CalendarDateResponse> list = repository.findNwdDateByEnabledAndNwdDateBetween(enabled, targetStartDt.toLocalDate(), targetEndDt.toLocalDate());

        assertFalse(list.isEmpty());
    }
}