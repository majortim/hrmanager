package kr.co.hrmanager.service.tna;

import kr.co.hrmanager.domain.tna.TnaType;
import kr.co.hrmanager.dto.tna.FindTnaCondition;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Slf4j
class TnaServiceTest {
    @Autowired
    TnaService tnaService;

    @Test
    void create() {
    }

    @Test
    @Sql({
            "/sql/data/employees.sql"
            , "/sql/data/tna.sql"
    })
    void toSetAllDates() {
        FindTnaCondition condition = FindTnaCondition.builder()
                .username("admin")
                .targetStartDt(LocalDateTime.of(2022, 1, 1, 1, 1))
                .targetEndDt(LocalDateTime.of(2022, 12, 31, 1, 1, 1))
                .tnaType(TnaType.ABSENCE_WITHOUT_LEAVE)
                .build();
        Set<LocalDate> set = tnaService.toSetAllDates(condition);
        log.debug("setDates: {}", set);

        assertFalse(set.isEmpty());
    }

    @Test
    //datesUntil 메서드가 종료일은 포함시키지 않기 때문에 LocalDate.plusDays(1) 을 이용했다.
    void datesUntil() {
        LocalDateTime startDt = LocalDateTime.of(2022, 1, 1, 1, 1);
        LocalDateTime endDt = LocalDateTime.of(2022, 1, 21, 23, 59);
        Set<LocalDate> set = startDt.toLocalDate().datesUntil(endDt.toLocalDate().plusDays(1)).collect(Collectors.toSet());

        log.debug("setDates: {}", set);

        assertFalse(set.isEmpty());
    }
}