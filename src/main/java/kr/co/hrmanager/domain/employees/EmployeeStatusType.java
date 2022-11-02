package kr.co.hrmanager.domain.employees;

import lombok.Getter;

@Getter
public enum EmployeeStatusType {
    PRESENT("재직"),
    ABSENT("휴직"),
    SUSPENDED("정직"),
    RETIRED("퇴직");

    private final String status;

    EmployeeStatusType(String status) {
        this.status = status;
    }

}
