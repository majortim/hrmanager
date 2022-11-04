package kr.co.hrmanager.domain.leaves;

import kr.co.hrmanager.domain.employees.Employees;

import java.time.LocalDateTime;
import java.util.List;

public interface LeavesQueryRepository {
    long countByEmployeeAndLeaveTypeListAndDateTime(Employees employee, List<LeaveType> leaveTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt);
}
