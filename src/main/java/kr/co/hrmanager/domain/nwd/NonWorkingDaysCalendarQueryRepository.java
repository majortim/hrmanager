package kr.co.hrmanager.domain.nwd;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface NonWorkingDaysCalendarQueryRepository {
    Stream<NonWorkingDaysCalendar> streamByYear(Integer year);
    List<NonWorkingDaysCalendar> listByDates(LocalDate startDate, LocalDate endDate);
}
