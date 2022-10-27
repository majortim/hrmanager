package kr.co.hrmanager.domain.employees;

import kr.co.hrmanager.domain.departments.Departments;
import kr.co.hrmanager.domain.jobs.Jobs;
import kr.co.hrmanager.domain.users.Users;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder
@Getter
@Entity
public class Employees {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long empId;

    @OneToOne
    @JoinColumn(name = "username")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Departments dept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Jobs job;

    private String empName;

    private String email;

    private String phoneNumber;

    private LocalDateTime hireDt;

    private LocalDateTime retireDt;
}
