package kr.co.hrmanager.domain.tna;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hrmanager.domain.employees.Employees;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static kr.co.hrmanager.domain.tna.QTna.tna;

@Repository
@RequiredArgsConstructor
public class TnaQueryRepositoryImpl implements TnaQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long countByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt) {
        return streamByEmployeeAndTnaTypeListAndDateTime(employee, tnaTypeList, prevYearDt, targetDt)
                .count();
    }

    @Override
    public List<Tna> listByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt) {
        return streamByEmployeeAndTnaTypeListAndDateTime(employee, tnaTypeList, prevYearDt, targetDt)
                .collect(Collectors.toList());
    }

    @Override
    public Stream<Tna> streamByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt) {
        return jpaQueryFactory.selectFrom(tna)
                .where(tna.employee.eq(employee))
                .where(tna.tnaTy.in(tnaTypeList))
                .where(
                        tna.startDt.between(prevYearDt, targetDt)
                                .or(tna.endDt.between(prevYearDt, targetDt))
                )
                .stream();
    }

    public List<Tna> listByPredicates(Predicate... predicates) {
        return jpaQueryFactory.selectFrom(tna)
                .where(predicates)
                .stream().collect(Collectors.toList());
    }
}
