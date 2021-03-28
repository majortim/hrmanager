package io.hurem.domain.employees;

import io.hurem.domain.departments.Departments;
import io.hurem.domain.jobs.Jobs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Employees {
    @Id
    @Column(name = "empId")
    private Long empId;

    @Column(name = "empName")
    private String empName;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "hireDate")
    private LocalDateTime hireDate;

    @Column(name = "retireDate")
    private LocalDateTime retireDate;

    @ManyToOne
    @JoinColumn(name = "jobId")
    private Jobs job;

    @ManyToOne
    @JoinColumn(name = "deptId")
    private Departments dept;

    @Column(name = "useYn", columnDefinition = "CHAR", length = 1)
    private String useYn;

    @Column(name = "delYn", columnDefinition = "CHAR", length = 1)
    private String delYn;

    @Builder
    public Employees(Long empId, String empName, String email, String phoneNumber, LocalDateTime hireDate, LocalDateTime retireDate, Jobs job, Departments dept, String useYn, String delYn) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.retireDate = retireDate;
        this.job = job;
        this.dept = dept;
        this.useYn = useYn;
        this.delYn = delYn;
    }
}
