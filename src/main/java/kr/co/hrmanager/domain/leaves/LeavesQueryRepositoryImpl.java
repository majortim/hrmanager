package kr.co.hrmanager.domain.leaves;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hrmanager.domain.employees.Employees;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static kr.co.hrmanager.domain.leaves.QLeaves.leaves;


@Repository
@RequiredArgsConstructor
public class LeavesQueryRepositoryImpl implements LeavesQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long countByEmployeeAndLeaveTypeListAndDateTime(Employees employee, List<LeaveType> leaveTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt) {
        return jpaQueryFactory.selectFrom(leaves)
                .where(leaves.employee.eq(employee))
                .where(leaves.leaveTy.in(leaveTypeList))
                .stream().count();
    }
}
