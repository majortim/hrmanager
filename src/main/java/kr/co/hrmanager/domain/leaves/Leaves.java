package kr.co.hrmanager.domain.leaves;

import kr.co.hrmanager.domain.employees.Employees;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder
@Getter
@Entity
public class Leaves {
    @Id
    private Long leaveId;

    @OneToOne
    @JoinColumn(name = "leave_id", referencedColumnName = "parent_id")
    private Leaves parentLeave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;

    private LeaveType leaveTy;

    private Long leaveCnt;

    private Boolean paid;

    private Boolean markedAsWorked;

    private LocalDateTime createDt;
}
