package kr.co.hrmanager.domain.nwd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface NonWorkingDaysCalendarRepository extends JpaRepository<NonWorkingDaysCalendar, Long> {
    long countByEnabledAndNwdDtBetween(Boolean enabled, LocalDateTime startDt, LocalDateTime endDt);
}
