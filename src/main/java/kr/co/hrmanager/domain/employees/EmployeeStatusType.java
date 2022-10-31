package kr.co.hrmanager.domain.employees;

import lombok.Getter;

@Getter
public enum EmployeeStatusType {
    PRESENT("재직"),
    ABSENT_PAID("유급휴직"),
    ABSENT_UNPAID("휴직"),
    PARENTAL("육아휴직"),
    SUSPENDED("정직"),
    RETIRED("퇴직");

    private final String status;

    EmployeeStatusType(String status) {
        this.status = status;
    }

}
