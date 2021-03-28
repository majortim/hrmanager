package io.hurem.domain.integration;

import io.hurem.domain.departments.Departments;
import io.hurem.domain.departments.DepartmentsRepository;
import io.hurem.domain.employees.Employees;
import io.hurem.domain.employees.EmployeesRepository;
import io.hurem.domain.jobs.Jobs;
import io.hurem.domain.jobs.JobsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DomainIntegrationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DepartmentsRepository departmentsRepository;

    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    EmployeesRepository employeesRepository;

    @AfterEach
    public void cleanup() {
        departmentsRepository.deleteAll();
        jobsRepository.deleteAll();
        employeesRepository.deleteAll();
    }

    public void saveDepartments() {
        long deptId = 1L;
        String deptName = "경영지원팀";
        String useYn = "Y";
        String delYn = "N";

        departmentsRepository.save(Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .useYn(useYn)
                .delYn(delYn)
                .build());
    }

    public void saveJobs() {
        long jobId = 1L;
        String jobTitle = "대리";
        String useYn = "Y";
        String delYn = "N";

        jobsRepository.save(Jobs.builder()
                .jobId(jobId)
                .jobTitle(jobTitle)
                .useYn(useYn)
                .delYn(delYn)
                .build());
    }

    public void saveEmployees(Departments dept, Jobs job) {
        long empId = 1L;
        String empName = "홍길동";
        String useYn = "Y";
        String delYn = "N";

        employeesRepository.save(Employees.builder()
                .empId(empId)
                .empName(empName)
                .dept(dept)
                .job(job)
                .useYn(useYn)
                .delYn(delYn)
                .build());
    }
    @Transactional
    @Test
    public void test(){
        saveDepartments();
        saveJobs();

        Departments department = departmentsRepository.getOne(1L);
        Jobs job = jobsRepository.getOne(1L);

        saveEmployees(department, job);

        Employees employee = employeesRepository.getOne(1L);

        logger.debug("name: {}, dept: {}, job: {}", employee.getEmpName(), employee.getDept().getDeptName(), employee.getJob().getJobTitle());
    }
}
