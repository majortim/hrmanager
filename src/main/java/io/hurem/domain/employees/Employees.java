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
    private Long empId;

    private String empName;

    private String email;

    private String phoneNumber;

    private LocalDateTime hireDate;

    private LocalDateTime retireDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "job_id")
    private Jobs job;

    @ManyToOne
    @JoinColumn
    private Departments dept;

    @Column( columnDefinition = "CHAR", length = 1)
    private String useYn;

    @Column(columnDefinition = "CHAR", length = 1)
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
