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
    private Integer deptId;

    private String deptName;

    private Long managerId;

    @Builder
    public Departments(Integer deptId, String deptName, Long managerId) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.managerId = managerId;
    }
}