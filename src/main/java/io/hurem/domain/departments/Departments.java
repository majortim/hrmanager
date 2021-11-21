package io.hurem.domain.departments;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Departments {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String deptName;

    private Long managerId;

    @Column(columnDefinition = "CHAR", length = 1)
    private String useYn;

    @Column(columnDefinition = "CHAR", length = 1)
    private String delYn;

    @Builder
    public Departments(Long deptId, String deptName, Long managerId, String useYn, String delYn) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.managerId = managerId;
        this.useYn = useYn;
        this.delYn = delYn;
    }
}