package kr.co.hrmanager.dto.employees;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateEmployeeRequest {
    @NotNull(message = "이름을 입력해주세요.")
    private String empName;
    private String username;
    private Long deptId;
    private Long jobId;
    private String email;
    private String phoneNumber;
    private LocalDateTime hireDt;
}
