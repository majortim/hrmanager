package io.hurem.domain.jobs;

import io.hurem.domain.employees.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Jobs, Long>  {

}