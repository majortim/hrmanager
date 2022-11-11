package kr.co.hrmanager.domain.employees;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Entity
@Table(name = "employee_status")
@EntityListeners(AuditingEntityListener.class)
public class EmployeeStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long esId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;
    @Enumerated(EnumType.STRING)
    private EmployeeStatusType esTy;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate scheduledEndDate;
    @CreatedDate
    private LocalDateTime createDt;
    private Boolean enabled;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeStatus that = (EmployeeStatus) o;
        return Objects.equals(esId, that.esId) && Objects.equals(employee.getEmpId(), that.employee.getEmpId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(esId, employee.getEmpId());
    }
}
