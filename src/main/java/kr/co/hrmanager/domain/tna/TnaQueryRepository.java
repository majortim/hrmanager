package kr.co.hrmanager.domain.tna;

import kr.co.hrmanager.domain.employees.Employees;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public interface TnaQueryRepository {
    long countByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt);
    List<Tna> listByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt);
    Stream<Tna> steamByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt);
}
