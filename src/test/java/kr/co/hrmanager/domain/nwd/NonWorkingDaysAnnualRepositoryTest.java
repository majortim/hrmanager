package kr.co.hrmanager.domain.nwd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NonWorkingDaysAnnualRepositoryTest {
    @Autowired
    NonWorkingDaysAnnualRepository repository;
    @Test
    @Sql("/sql/data/nwd_annual.sql")
    void findAllByEnabled() {
        final Boolean enabled = true;

        List<NonWorkingDaysAnnual> list = repository.findAllByEnabled(enabled);

        assertFalse(list.isEmpty());
    }

}