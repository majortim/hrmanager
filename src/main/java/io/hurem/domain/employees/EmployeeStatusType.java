package io.hurem.domain.employees;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EmployeeStatusType {
    PRESENT("재직"),
    ABSENT("일반휴직"),
    PARENTAL("육아휴직"),
    UNPAID("무급휴직"),
    SUSPENDED("정직"),
    RETIRED("퇴직"),
    UNKNOWN("알 수 없음");

    private final String status;

    EmployeeStatusType(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    private static final Map<String, EmployeeStatusType> types = Collections.unmodifiableMap(Stream.of(values()) .collect(Collectors.toMap(EmployeeStatusType::toString, Function.identity())));

    public static EmployeeStatusType find(String description) { return Optional.ofNullable(types.get(description)).orElse(UNKNOWN); }
}
