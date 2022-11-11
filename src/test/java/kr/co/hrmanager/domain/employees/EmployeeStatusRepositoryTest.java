package kr.co.hrmanager.domain.employees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Slf4j
public class EmployeeStatusRepositoryTest {
    @Autowired EmployeesRepository employeesRepository;
    @Autowired EmployeeStatusRepository repository;

    @Test
    void saveRetired() {
        //given
        Employees employee = Employees.builder()
                .empName("테스트사원")
                .build();

        Employees savedEmployee = employeesRepository.save(employee);

        EmployeeStatus employeeStatus = EmployeeStatus.builder()
                .employee(savedEmployee)
                .createDt(LocalDateTime.of(LocalDate.of(2022,2, 10), LocalTime.now()))
                .startDate(LocalDate.of(2022, 2, 11))
                .esTy(EmployeeStatusType.RETIRED)
                .enabled(true)
                .build();
        //when
        EmployeeStatus saved = repository.save(employeeStatus);
        //then
        assertEquals(EmployeeStatusType.RETIRED, saved.getEsTy());
    }
    @Test
    @Transactional
    void findAllByEnabledAndDates() {
        //given
        Boolean enabled = true;
        LocalDate targetStartDate = LocalDate.of(2022, 2, 4);
        LocalDate targetEndDate = LocalDate.of(2022, 7, 12);

        //when
        Stream<EmployeeStatus> stream = repository.findAllByEnabledAndDates(enabled, targetStartDate, targetEndDate);

        //then
        assertTrue(stream.findAny().isEmpty());
    }

}
