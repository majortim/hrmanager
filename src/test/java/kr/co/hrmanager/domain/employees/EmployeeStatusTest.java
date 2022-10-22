package kr.co.hrmanager.domain.employees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class EmployeeStatusTest {
    @Autowired EmployeesRepository employeesRepository;
    @Autowired EmployeeStatusRepository repository;

    @Test
    public void EmployeeStatusTypeSaveTest() {
        //given

        Employees employee = Employees.builder()
                .empId(1L)
                .empName("테스트사원")
                .build();

        Employees savedEmployee = employeesRepository.save(employee);

        EmployeeStatus employeeStatus = EmployeeStatus.builder()
                .esId(1L)
                .employee(savedEmployee)
                .createDt(LocalDateTime.of(LocalDate.of(2022,2, 10), LocalTime.now()))
                .esTy(EmployeeStatusType.RETIRED)
                .build();
        //when
        EmployeeStatus saved = repository.save(employeeStatus);
        //then
        assertEquals(EmployeeStatusType.RETIRED, saved.getEsTy());

    }


}
