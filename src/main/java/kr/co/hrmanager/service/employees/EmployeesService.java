package kr.co.hrmanager.service.employees;

import kr.co.hrmanager.domain.departments.Departments;
import kr.co.hrmanager.domain.departments.DepartmentsRepository;
import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.jobs.Jobs;
import kr.co.hrmanager.domain.jobs.JobsRepository;
import kr.co.hrmanager.domain.users.Users;
import kr.co.hrmanager.domain.users.UsersRepository;
import kr.co.hrmanager.web.dto.employees.CreateEmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeesService {

    private final UsersRepository usersRepository;
    private final EmployeesRepository employeesRepository;
    private final DepartmentsRepository departmentsRepository;
    private final JobsRepository jobsRepository;

    public Optional<Employees> findById(Long id) {
        return employeesRepository.findById(id);
    }

    public Optional<Employees> findByUser(Users user) {
        return employeesRepository.findByUser(user);
    }

    public List<Employees> findByAll(Example<Employees> queryParam) {
        return employeesRepository.findAll(queryParam);
    }

    @Transactional
    public Employees create(CreateEmployeeRequest createEmployeeRequest) {

        Employees.EmployeesBuilder employeeBuilder = Employees.builder()
                .empName(createEmployeeRequest.getEmpName())
                .email(createEmployeeRequest.getEmail())
                .phoneNumber(createEmployeeRequest.getPhoneNumber())
                .hireDt(createEmployeeRequest.getHireDt());

        Optional<Users> optionalUser = Optional.ofNullable(createEmployeeRequest.getUsername())
                .flatMap(usersRepository::findById);
        Optional<Departments> optionalDept = Optional.ofNullable(createEmployeeRequest.getDeptId())
                .flatMap(departmentsRepository::findById);
        Optional<Jobs> optionalJob = Optional.ofNullable(createEmployeeRequest.getJobId())
                .flatMap(jobsRepository::findById);

        optionalUser.ifPresent(employeeBuilder::user);
        optionalDept.ifPresent(employeeBuilder::dept);
        optionalJob.ifPresent(employeeBuilder::job);

        Employees employee = employeeBuilder.build();

        return employeesRepository.save(employee);
   }
}
