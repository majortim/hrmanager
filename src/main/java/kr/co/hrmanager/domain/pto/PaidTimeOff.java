package kr.co.hrmanager.domain.pto;

import kr.co.hrmanager.domain.employees.Employees;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder
@Getter
@Table(name = "paid_time_off")
@Entity
public class PaidTimeOff {
    @Id
    private Long ptoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;

    private PaidTimeOffType ptoTy;

    private Long ptoCnt;

    private LocalDateTime createDt;
}
