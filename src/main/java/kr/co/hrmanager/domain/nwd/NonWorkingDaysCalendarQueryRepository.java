package kr.co.hrmanager.domain.nwd;

import java.util.stream.Stream;

public interface NonWorkingDaysCalendarQueryRepository {
    Stream<NonWorkingDaysCalendar> streamByYear(Integer year);
    //Stream<NonWorkingDaysCalendar> streamByAnnualYear(Integer year);
    //Stream<NonWorkingDaysCalendar> streamByWeeklyYear(Integer year);
    //Stream<NonWorkingDaysCalendar> streamByTemporaryYear(Integer year);
}
