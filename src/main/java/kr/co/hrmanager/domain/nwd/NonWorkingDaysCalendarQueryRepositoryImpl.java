package kr.co.hrmanager.domain.nwd;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
