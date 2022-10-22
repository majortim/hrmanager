package kr.co.hrmanager.domain.employees;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Entity
@Table(name = "employee_status")
public class EmployeeStatus {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long esId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;
    @Enumerated(EnumType.STRING)
    private EmployeeStatusType esTy;
    private LocalDateTime createDt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeStatus that = (EmployeeStatus) o;
        return Objects.equals(esId, that.esId) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esId, employee);
    }
}
