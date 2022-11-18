package kr.co.hrmanager.domain.leaves;

import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
public class LeavesRepositoryTest {
    @Autowired
    LeavesRepository leavesRepository;
    @Autowired
    EmployeesRepository employeesRepository;

    @Test
    @Sql("/sql/data/employees.sql")
    void save() {
        Employees employee = employeesRepository.findByEmpName("관리자").orElseThrow();

        Leaves parentalLeaves = Leaves.builder()
                .employee(employee)
                .baseYear(2022)
                .leaveTy(LeaveType.PARENTAL)
                .leaveCnt(5L)
                .paid(true)
                .markedAsWorked(true)
                .enabled(true)
                .build();
        Leaves officialLeaves = Leaves.builder()
                .employee(employee)
                .baseYear(2022)
                .leaveTy(LeaveType.OFFICIAL)
                .leaveCnt(3L)
                .paid(true)
                .markedAsWorked(true)
                .enabled(false)
                .build();

        Leaves savedParental = leavesRepository.save(parentalLeaves);
        Leaves savedOfficial = leavesRepository.save(officialLeaves);

        log.debug("parental id: {}, official id: {}", savedParental.getLeaveId(), savedOfficial.getLeaveId());
        assertNotEquals(savedParental.getLeaveId(), savedOfficial.getLeaveId());
        assertNotEquals(savedParental.getEnabled(), savedOfficial.getEnabled());
    }
}
