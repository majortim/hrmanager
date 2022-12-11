package kr.co.hrmanager.domain.tna;

import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.leaves.LeaveType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
public class Tna {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long tnaId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;
    private Integer baseYear;
    @Enumerated(EnumType.STRING)
    private TnaType tnaTy;
    @Enumerated(EnumType.STRING)
    private LeaveType leaveTy;
    private LocalDateTime startDt;
    private LocalDateTime endDt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tna tna = (Tna) o;
        return Objects.equals(tnaId, tna.tnaId) && Objects.equals(employee, tna.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tnaId);
    }

    public Set<LocalDate> toSetAllDates(LocalDate targetStartDate, LocalDate targetEndDate) {
        final LocalDate tnaStartDate = this.startDt.toLocalDate();
        final LocalDate tnaEndDate = this.endDt.toLocalDate();

        LocalDate startDate = tnaStartDate.isAfter(targetStartDate) ? tnaStartDate : targetStartDate;
        LocalDate endDate = tnaEndDate.isBefore(targetEndDate) ? tnaEndDate : targetEndDate;

        return startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toSet());
    }
}
