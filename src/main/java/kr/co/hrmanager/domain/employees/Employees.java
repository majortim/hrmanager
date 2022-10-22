package kr.co.hrmanager.domain.employees;

import kr.co.hrmanager.domain.departments.Departments;
import kr.co.hrmanager.domain.jobs.Jobs;
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
    private Long empId;

    private String empName;

    private String email;

    private String phoneNumber;

    private LocalDateTime hireDt;

    private LocalDateTime retireDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Jobs job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Departments dept;
}
