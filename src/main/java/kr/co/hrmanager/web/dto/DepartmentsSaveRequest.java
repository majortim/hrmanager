package kr.co.hrmanager.web.dto;

import kr.co.hrmanager.domain.departments.Departments;
import lombok.*;


@NoArgsConstructor

@Getter
public class DepartmentsSaveRequest {
    private Long deptId;
    private String deptName;
    private Long managerId;


    @Builder
    public DepartmentsSaveRequest(Long deptId, String deptName, Long managerId) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.managerId = managerId;
    }

    public Departments toEntity(){
        return Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .build();
    }
}