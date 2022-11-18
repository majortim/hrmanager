package kr.co.hrmanager.domain.leaves;

import kr.co.hrmanager.domain.common.BaseEntity;
import kr.co.hrmanager.domain.employees.Employees;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Getter
@Entity
public class Leaves extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    @OneToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "parent_id")
    private Leaves parentLeave;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;
    private Integer baseYear;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveTy;
    private Long leaveCnt;
    private Boolean paid;
    private Boolean markedAsWorked;
}
