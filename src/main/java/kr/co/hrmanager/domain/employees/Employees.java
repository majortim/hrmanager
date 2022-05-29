package io.hurem.domain.employees;

import io.hurem.domain.departments.Departments;
import io.hurem.domain.jobs.Jobs;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Entity
public class Employees {
    @Id
    private Integer empId;

    private String empName;

    private String email;

    private String phoneNumber;

    private LocalDateTime hireDate;

    private LocalDateTime retireDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Jobs job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Departments dept;
}
