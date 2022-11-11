package kr.co.hrmanager.domain.employees;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface EmployeeStatusQueryRepository {
    Stream<EmployeeStatus> findAllByEnabledAndDates(Boolean enabled, LocalDate targetStartDt, LocalDate targetEndDt);
}
