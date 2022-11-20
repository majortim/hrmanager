package kr.co.hrmanager.domain.tna;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hrmanager.domain.employees.Employees;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    //TODO count 수정
    public long countByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt) {
        return steamByEmployeeAndTnaTypeListAndDateTime(employee, tnaTypeList, prevYearDt, targetDt)
                .count();
    }

    @Override
    public List<Tna> listByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt) {
        return steamByEmployeeAndTnaTypeListAndDateTime(employee, tnaTypeList, prevYearDt, targetDt)
                .collect(Collectors.toList());
    }

    public List<LocalDate> listAllDates(Stream<Tna> streamTna) {
        return null;
    }

    @Override
    public Stream<Tna> steamByEmployeeAndTnaTypeListAndDateTime(Employees employee, List<TnaType> tnaTypeList, LocalDateTime prevYearDt, LocalDateTime targetDt) {
        return jpaQueryFactory.selectFrom(tna)
                .where(tna.employee.eq(employee))
                .where(tna.tnaTy.in(tnaTypeList))
                .where(
                        tna.startDt.between(prevYearDt, targetDt)
                                .or(tna.endDt.between(prevYearDt, targetDt))
                )
                .stream();
    }
}
