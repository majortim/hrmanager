package kr.co.hrmanager.domain.integration;

import kr.co.hrmanager.domain.departments.Departments;
import kr.co.hrmanager.domain.departments.DepartmentsRepository;
import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.jobs.Jobs;
import kr.co.hrmanager.domain.jobs.JobsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DepartmentsJobsEmployeesIntegrationTest {

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @AfterEach
    void cleanup() {
        departmentsRepository.deleteAll();
        jobsRepository.deleteAll();
        employeesRepository.deleteAll();
    }
    @Transactional
    @Test
    void saveDepartmentsAndJobsAndEmployees(){
        saveDepartments();
        saveJobs();

        Departments department = departmentsRepository.getReferenceById(1L);
        Jobs job = jobsRepository.getReferenceById(1L);

        saveEmployees(department, job);

        Employees employee = employeesRepository.getReferenceById(1L);

        assertNotNull(employee);
    }

    private void saveDepartments() {
        Long deptId = 1L;
        String deptName = "경영지원팀";

        departmentsRepository.save(Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .build());
    }

    private void saveJobs() {
        Long jobId = 1L;
        String jobTitle = "대리";

        jobsRepository.save(Jobs.builder()
                .jobId(jobId)
                .jobTitle(jobTitle)
                .build());
    }

    private void saveEmployees(Departments dept, Jobs job) {
        String empName = "홍길동";

        employeesRepository.save(Employees.builder()
                .empName(empName)
                .dept(dept)
                .job(job)
                .build());
    }
}
