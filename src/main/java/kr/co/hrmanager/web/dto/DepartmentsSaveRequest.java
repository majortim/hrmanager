package io.hurem.web.dto;

import io.hurem.domain.departments.Departments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentsSaveRequest {
    private Integer deptId;
    private String deptName;
    private Integer managerId;


    @Builder
    public DepartmentsSaveRequest(Integer deptId, String deptName, Integer managerId) {
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