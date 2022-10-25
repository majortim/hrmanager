package kr.co.hrmanager.service.employees;

import kr.co.hrmanager.domain.employees.Employees;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class EmployeesServiceTest {

    @Autowired
    EmployeesService employeesService;

    @Test
    @Sql("/sql/data/employees.sql")
    void findById() {
        Long employeeId = 1L;

        Employees employee = employeesService.findById(employeeId).orElseThrow();

        assertEquals(employeeId, employee.getEmpId());
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
}