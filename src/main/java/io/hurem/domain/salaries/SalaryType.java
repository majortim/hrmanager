package io.hurem.domain.salaries;

public enum SalaryType {
    EARN("급여"),
    DEDUCE("공제");

    private final String type;

    SalaryType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
