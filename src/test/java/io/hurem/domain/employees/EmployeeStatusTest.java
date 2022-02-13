package io.hurem.domain.employees;

import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@Slf4j
@SpringBootTest
public class EmployeeStatusTest {
    @Autowired EmployeeStatusRepository repository;

    @Test
    public void EmployeeStatusTypeTest() {
        assertEquals("재직", EmployeeStatusType.find("재직").toString());
        assertEquals("알 수 없음", EmployeeStatusType.find("테스트").toString());
    }
    @Test
    public void EmployeeStatusTypeSaveTest() {
        //given

/*
        EmployeeStatus employeeStatus = EmployeeStatus.builder()
                .esId(1L)
                .empId(1)
                .createDate(LocalDate.of(2022,2, 10))
                .esTy(EmployeeStatusType.RETIRED)
                .build();
        //when
        EmployeeStatus saved = repository.save(employeeStatus);
        //then
        assertEquals("RETIRED", saved.getEsTy().name());
        */
    }


}
