package kr.co.hrmanager.domain.nwd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class NonWorkingDaysCalendarlRepositoryTest {
    @Autowired
    NonWorkingDaysCalendarRepository repository;
    @Test
    void countByEnabledAndNwdDateBetween() {
        Boolean enabled = true;
        LocalDateTime targetStartDt = LocalDateTime.of(2022, 3, 1, 1, 1);
        LocalDateTime targetEndDt = targetStartDt.plusYears(1).plusDays(1).minusNanos(1);

        long count = repository.countByEnabledAndNwdDateBetween(enabled, targetStartDt.toLocalDate(), targetEndDt.toLocalDate());

        assertEquals(0, count);
    }
}