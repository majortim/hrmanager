package kr.co.hrmanager.domain.tna;

import java.time.LocalDateTime;

public interface TnaQueryRepository {
    long countByTnaTypeAndDateTime(TnaType tnaType, LocalDateTime startDt, LocalDateTime endDt);
}
