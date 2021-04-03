package io.hurem.web.dto;

import io.hurem.domain.departments.Departments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class DepartmentsSaveRequest {
    @Id
    @Column(name = "deptId")
    private Long deptId;

    @Column(name = "deptName")
    private String deptName;

    @Column(name = "managerId")
    private Long managerId;

    @Column(name = "useYn", columnDefinition = "CHAR", length = 1)
    private String useYn;

    @Column(name = "delYn", columnDefinition = "CHAR", length = 1)
    private String delYn;

    @Builder
    public DepartmentsSaveRequest(Long deptId, String deptName, Long managerId, String useYn, String delYn) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.managerId = managerId;
        this.useYn = useYn;
        this.delYn = delYn;
    }

    public Departments toEntity(){
        return Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .useYn(useYn)
                .delYn(delYn)
                .build();
    }
}