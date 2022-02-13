package io.hurem.domain.tna;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TnaType {
    DAY("휴가"),
    MORNING("오전 반차"),
    AFTERNOON("오후 반차"),
    MINUTE("시간 지정"),
    UNKNOWN("알 수 없음");

    private final String status;

    TnaType(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    private static final Map<String, TnaType> types = Collections.unmodifiableMap(Stream.of(values()) .collect(Collectors.toMap(TnaType::toString, Function.identity())));

    public static TnaType find(String description) { return Optional.ofNullable(types.get(description)).orElse(UNKNOWN); }

}
