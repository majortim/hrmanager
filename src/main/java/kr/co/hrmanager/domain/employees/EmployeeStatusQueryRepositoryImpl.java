package kr.co.hrmanager.domain.employees;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.stream.Stream;

import static kr.co.hrmanager.domain.employees.QEmployeeStatus.employeeStatus;

@Repository
@RequiredArgsConstructor
public class EmployeeStatusQueryRepositoryImpl implements EmployeeStatusQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Stream<EmployeeStatus> findAllByEnabledAndDates(Boolean enabled, LocalDate targetStartDate, LocalDate targetEndDate) {

        BooleanBuilder booleanBuilder = new BooleanBuilder()
/*
                .or(
                        employeeStatus.startDate.before(targetStartDate)
                                .and(employeeStatus.startDate.after(employeeStatus.endDate).not())
                                .and(employeeStatus.endDate.after(targetEndDate).not())
                )
 */
                    .or(
                            employeeStatus.startDate.before(targetStartDate).not()
                                    .and(
                                            employeeStatus.startDate.after(employeeStatus.endDate).not().and(employeeStatus.endDate.after(targetEndDate).not())
                                        .or(
                                                employeeStatus.startDate.after(targetEndDate).not().and(employeeStatus.endDate.before(targetEndDate).not())
                                        )
                                    )
                     )
                    .or(
                            employeeStatus.startDate.after(targetStartDate).not()
                                    .and(
                                            compareDate(targetStartDate, targetEndDate).and(employeeStatus.endDate.before(targetEndDate).not())
                                                    .or(
                                                            employeeStatus.endDate.before(targetStartDate).not().and(employeeStatus.endDate.after(targetEndDate).not())
                                                    )
                                    )
                    )
                ;

        return
            jpaQueryFactory
                    .selectFrom(employeeStatus)
                    .where(employeeStatus.enabled.eq(enabled))
                    .where(booleanBuilder)
                    .stream();
    }

    private BooleanExpression compareDate(LocalDate target, LocalDate after) {
        return  Expressions.asBoolean(after.isAfter(target)).isTrue();
    }
}
