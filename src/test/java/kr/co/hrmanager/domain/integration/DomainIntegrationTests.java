package kr.co.hrmanager.domain.integration;

import kr.co.hrmanager.domain.departments.Departments;
import kr.co.hrmanager.domain.departments.DepartmentsRepository;
import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.jobs.Jobs;
import kr.co.hrmanager.domain.jobs.JobsRepository;

import kr.co.hrmanager.domain.salaries.SalaryParticulars;
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
        int deptId = 1;
        String deptName = "경영지원팀";

        departmentsRepository.save(Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .build());
    }

    public void saveJobs() {
        int jobId = 1;
        String jobTitle = "대리";

        jobsRepository.save(Jobs.builder()
                .jobId(jobId)
                .jobTitle(jobTitle)
                .build());
    }

    public void saveEmployees(Departments dept, Jobs job) {
        int empId = 1;
        String empName = "홍길동";

        employeesRepository.save(Employees.builder()
                .empId(empId)
                .empName(empName)
                .dept(dept)
                .job(job)
                .build());
    }
    @Transactional
    @Test
    public void test(){
        saveDepartments();
        saveJobs();

        Departments department = departmentsRepository.getById(1);
        Jobs job = jobsRepository.getById(1);

        saveEmployees(department, job);

        Employees employee = employeesRepository.getById(1);


        logger.debug("name: {}, dept: {}, job: {}", employee.getEmpName(), employee.getDept().getDeptName(), employee.getJob().getJobTitle());
    }
}
