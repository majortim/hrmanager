package kr.co.hrmanager.domain.nwd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface NonWorkingDaysCalendarRepository extends JpaRepository<NonWorkingDaysCalendar, Long>, NonWorkingDaysCalendarQueryRepository {
    long countByEnabledAndNwdDateBetween(Boolean enabled, LocalDate startDate, LocalDate endDate);
}
