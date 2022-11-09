package kr.co.hrmanager.domain.nwd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NonWorkingDaysWeeklyRepository extends JpaRepository<NonWorkingDaysWeekly, Long> {
    List<NonWorkingDaysWeekly> findAllByEnabled(Boolean enabled);
}
