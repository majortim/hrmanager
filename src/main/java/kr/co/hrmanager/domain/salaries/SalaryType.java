package kr.co.hrmanager.domain.salaries;

import lombok.Getter;

@Getter
public enum SalaryType {
    EARN("급여"),
    DEDUCE("공제");

    private final String type;

    SalaryType(String type) {
        this.type = type;
    }
}
