package kr.co.hrmanager.domain.tna;

import kr.co.hrmanager.dto.tna.FindTnaCondition;

import java.util.List;
import java.util.stream.Stream;

public interface TnaQueryRepository {
    Long countByCondition(FindTnaCondition condition);
    List<Tna> listByCondition(FindTnaCondition condition);
    Stream<Tna> streamByCondition(FindTnaCondition condition);
}
