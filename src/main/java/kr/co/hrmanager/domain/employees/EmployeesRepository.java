package kr.co.hrmanager.domain.employees;

import kr.co.hrmanager.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employees, Long>  {
    Optional<Employees> findByUser(Users user);
}