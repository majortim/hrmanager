package kr.co.hrmanager.domain.employees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeStatusRepository extends JpaRepository<EmployeeStatus, Long>, EmployeeStatusQueryRepository {
    List<EmployeeStatus> findAllByEnabled(Boolean enabled);
}
