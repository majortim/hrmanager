package kr.co.hrmanager.domain.integration;

import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.tna.Tna;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.domain.tna.TnaType;
import kr.co.hrmanager.dto.tna.FindTnaCondition;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Slf4j
class TnaEmployeesIntegrationTest {

    @Autowired
    TnaRepository tnaRepository;
    @Autowired
    EmployeesRepository employeesRepository;

    @Test
    @Transactional(readOnly = true)
    @Sql({
            "/sql/data/employees.sql"
            , "/sql/data/tna.sql"
    })
    void streamByEmployeeAndTnaTypeListAndDateTime() {
        FindTnaCondition condition = createCondition();

        Stream<Tna> streamTna = createStream(condition);
        List<Tna> list = streamTna.collect(Collectors.toList());

        assertFalse(list.isEmpty());
    }

    FindTnaCondition createCondition() {
        return  FindTnaCondition.builder()
                .username("admin")
                .targetStartDt(LocalDateTime.of(2021, 1, 1, 1, 1))
                .targetEndDt(LocalDateTime.of(2021, 12, 31, 1, 1, 1))
                .tnaType(TnaType.ABSENCE_WITHOUT_LEAVE)
                .build();
    }

    Stream<Tna> createStream(FindTnaCondition condition) {

        return tnaRepository.streamByCondition(condition);
    }
}