package kr.co.hrmanager.domain.employees;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
        setupTestData();
        Stream<EmployeeStatus> stream = repository.findAllByEnabledAndDates(enabled, targetStartDate, targetEndDate);
        List<EmployeeStatus> list = stream.collect(Collectors.toList());

        //then
        assertTrue(list.size() > 0);
    }

    private void setupTestData() {
        Employees employee = Employees.builder()
                .empName("테스트사원")
                .build();
        Employees savedEmployee = employeesRepository.save(employee);

        EmployeeStatus status1 = buildEmployeeStatus(savedEmployee, LocalDate.of(2021, 10, 28), LocalDate.of(2022, 2, 3), EmployeeStatusType.PRESENT);
        EmployeeStatus status2 = buildEmployeeStatus(savedEmployee, LocalDate.of(2022, 2, 4), LocalDate.of(2022, 3, 4), EmployeeStatusType.ABSENT);
        EmployeeStatus status3 = buildEmployeeStatus(savedEmployee, LocalDate.of(2022, 3, 5), LocalDate.of(2022, 9, 2), EmployeeStatusType.PRESENT);
        EmployeeStatus status4 = buildEmployeeStatus(savedEmployee, LocalDate.of(2022, 9, 3), null, EmployeeStatusType.RETIRED);
        List<EmployeeStatus> list = List.of(status1, status2, status3, status4);
        repository.saveAll(list);
    }

    private EmployeeStatus buildEmployeeStatus(Employees employee, LocalDate startDate, LocalDate endDate, EmployeeStatusType esTy) {

        EmployeeStatus.EmployeeStatusBuilder builder = EmployeeStatus.builder()
                .employee(employee)
                .startDate(startDate)
                .esTy(esTy)
                .enabled(true);

        return Optional.of(endDate).map(builder::endDate).orElse(builder).build();

    }
}
