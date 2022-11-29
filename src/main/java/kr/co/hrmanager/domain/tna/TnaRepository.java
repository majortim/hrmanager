package kr.co.hrmanager.domain.tna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TnaRepository extends JpaRepository<Tna, Long>, TnaQueryRepository {
}
