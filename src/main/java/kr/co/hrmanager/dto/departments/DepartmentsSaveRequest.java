package kr.co.hrmanager.dto.departments;

import kr.co.hrmanager.domain.departments.Departments;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class DepartmentsSaveRequest {
    private Long deptId;
    private String deptName;
    private Long managerId;

    public Departments toEntity(){
        return Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .build();
    }
}