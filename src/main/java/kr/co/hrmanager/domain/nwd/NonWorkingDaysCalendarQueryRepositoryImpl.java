package kr.co.hrmanager.domain.nwd;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static kr.co.hrmanager.domain.nwd.QNonWorkingDaysCalendar.nonWorkingDaysCalendar;

@Repository
@RequiredArgsConstructor
public class NonWorkingDaysCalendarQueryRepositoryImpl implements NonWorkingDaysCalendarQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Stream<NonWorkingDaysCalendar> streamByYear(Integer year) {
        return jpaQueryFactory.selectFrom(nonWorkingDaysCalendar)
                .where(nonWorkingDaysCalendar.enabled.eq(true))
                .where(nonWorkingDaysCalendar.nwdDate.year().eq(year))
                .stream();

    }

    @Override
    public List<NonWorkingDaysCalendar> listByDates(LocalDate startDate, LocalDate endDate) {
        return jpaQueryFactory.selectFrom(nonWorkingDaysCalendar)
                .where(nonWorkingDaysCalendar.nwdDate.between(startDate, endDate))
                .where(nonWorkingDaysCalendar.enabled.isTrue())
                .stream().collect(Collectors.toList());
    }
}
