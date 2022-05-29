package kr.co.hrmanager.domain.employees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EmployeeStatusTest {
    @Autowired EmployeeStatusRepository repository;

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
