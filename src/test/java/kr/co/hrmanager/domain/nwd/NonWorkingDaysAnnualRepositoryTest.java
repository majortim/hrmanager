package kr.co.hrmanager.domain.nwd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NonWorkingDaysAnnualRepositoryTest {
    @Autowired
    NonWorkingDaysAnnualRepository repository;
    @Test
    void findAllByEnabled() {
        log.debug("true: {}", repository.findAllByEnabled(true));
        log.debug("false: {}", repository.findAllByEnabled(false));
    }
}