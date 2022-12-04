package kr.co.hrmanager.service.employees;

import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.dto.employees.CreateEmployeeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class EmployeesServiceTest {

    @Autowired
    EmployeesService employeesService;

    @Test
    @Sql("/sql/data/employees.sql")
    void findById() {
        Long employeeId = 1L;

        Optional<Employees> employee = employeesService.findById(employeeId);
                //.orElseThrow();
        employee.ifPresent(e -> assertEquals(employeeId, e.getEmpId()));
    }

    @Test
    @Sql("/sql/data/employees.sql")
    void findByAll() {

        Employees employeeToFind = Employees.builder()
                .empName("관리자")
                .build();

        Example<Employees> queryParam = Example.of(employeeToFind);

        List<Employees> employeesList = employeesService.findByAll(queryParam);

        assertFalse(CollectionUtils.isEmpty(employeesList));
    }

    @Test
    @Sql({
              "/sql/data/departments.sql"
            , "/sql/data/employees.sql"
    })
    void create() {
        CreateEmployeeRequest createEmployeeRequest = CreateEmployeeRequest.builder()
                .empName("키위")
                .username("admin")
                .email("test@abc.com")
                .phoneNumber("123")
                .deptId(1L)
                .build();

        assertDoesNotThrow(() -> employeesService.create(createEmployeeRequest));
    }
}