package kr.co.hrmanager.domain.tna;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hrmanager.domain.leaves.LeaveType;
import kr.co.hrmanager.dto.tna.FindTnaCondition;
import kr.co.hrmanager.dto.tna.TnaDateResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static kr.co.hrmanager.domain.tna.QTna.tna;

@Repository
@RequiredArgsConstructor
public class TnaQueryRepositoryImpl implements TnaQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long countByCondition(FindTnaCondition condition) {
        return queryByCondition(jpaQueryFactory.select(tna.count()).from(tna), condition)
                .fetchOne();
    }

    @Override
    public List<Tna> listByCondition(FindTnaCondition condition) {
        return queryByCondition(jpaQueryFactory.selectFrom(tna), condition)
                .fetch();
    }

    public Stream<TnaDateResponse> streamTnaDateResponseByCondition(FindTnaCondition condition) {
        return queryByCondition(jpaQueryFactory.select(Projections.constructor(TnaDateResponse.class, tna.startDt, tna.endDt)).from(tna), condition)
                .stream();
    }

    @Override
    public Stream<Tna> streamByCondition(FindTnaCondition condition) {
        return queryByCondition(jpaQueryFactory.selectFrom(tna), condition)
                .stream();
    }

    private <T> JPAQuery<T> queryByCondition(JPAQuery<T> query, FindTnaCondition condition) {
        return query
                .where(usernameEq(condition.getUsername()))
                .where(tnaTypeEq(condition.getTnaType()))
                .where(leaveTypeEq(condition.getLeaveType()))
                .where(
                        dateTimeRange(condition.getTargetStartDt(), condition.getTargetEndDt())
                );
    }

    private BooleanExpression usernameEq(String username) {
        return StringUtils.isNotEmpty(username) ? tna.employee.user.username.eq(username) : null;
    }

    private BooleanExpression tnaTypeEq(TnaType tnaType) {
        return tnaType != null ? tna.tnaTy.eq(tnaType) : null;
    }

    private BooleanExpression leaveTypeEq(LeaveType leaveType) {
        return leaveType != null ? tna.leaveTy.eq(leaveType) : null;
    }

    private BooleanExpression dateTimeRange(LocalDateTime startDt, LocalDateTime endDt) {
        return startDt != null && endDt != null ? tna.startDt.between(startDt, endDt)
                .or(tna.endDt.between(startDt, endDt)) : null;
    }
}
