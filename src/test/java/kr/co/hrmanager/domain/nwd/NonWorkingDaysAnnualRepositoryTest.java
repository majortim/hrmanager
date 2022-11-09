package kr.co.hrmanager.domain.nwd;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NonWorkingDaysAnnualRepositoryTest {
    @Autowired
    NonWorkingDaysAnnualRepository repository;
    @Test
    void findAllByEnabled() {
        final Boolean enabled = true;
        List<NonWorkingDaysAnnual> list = repository.findAllByEnabled(enabled);
        //결과가 없더라도 null이 아니라 요소가 없는 리스트를 반환하다.
        assertNotNull(list);
    }
}