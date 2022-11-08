package kr.co.hrmanager.domain.nwd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NonWorkingDaysAnnualRepository extends JpaRepository<NonWorkingDaysAnnual, Long> {
    List<NonWorkingDaysAnnual> findAllByEnabled(Boolean enabled);
}
