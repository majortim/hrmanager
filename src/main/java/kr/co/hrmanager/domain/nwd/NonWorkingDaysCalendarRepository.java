package kr.co.hrmanager.domain.nwd;

import kr.co.hrmanager.dto.nwd.CalendarDateResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NonWorkingDaysCalendarRepository extends JpaRepository<NonWorkingDaysCalendar, Long>, NonWorkingDaysCalendarQueryRepository {
    long countByEnabledAndNwdDateBetween(Boolean enabled, LocalDate startDate, LocalDate endDate);
    List<CalendarDateResponse> findNwdDateByEnabledAndNwdDateBetween(Boolean enabled, LocalDate startDate, LocalDate endDate);
}
