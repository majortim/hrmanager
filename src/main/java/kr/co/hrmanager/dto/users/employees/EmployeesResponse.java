package kr.co.hrmanager.dto.users.employees;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmployeesResponse {
    private Long empId;

    private String empName;

    private String department;

    private String job;

    private String email;

    private String phoneNumber;

    private LocalDateTime hireDt;

    private LocalDateTime retireDt;
}
