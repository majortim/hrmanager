package kr.co.hrmanager.domain.tna;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static kr.co.hrmanager.domain.tna.QTna.tna;

@Repository
@RequiredArgsConstructor
public class TnaQueryRepositoryImpl implements TnaQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long countByTnaTypeAndDateTime(TnaType tnaType, LocalDateTime startDt, LocalDateTime endDt) {
        return jpaQueryFactory.selectFrom(tna)
                .where(
                        tna.tnaTy.eq(tnaType.getType())
                                .and(
                                        tna.startDt.between(startDt, endDt)
                                                .or(tna.endDt.between(startDt, endDt))
                                )
                )
                .stream().count()
                ;
    }
}
