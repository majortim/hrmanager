package kr.co.hrmanager.domain.nwd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NonWorkingDaysTemporaryRepository extends JpaRepository<NonWorkingDaysTemporary, Long> {
    List<NonWorkingDaysTemporary> findAllByEnabled(Boolean enabled);
}
