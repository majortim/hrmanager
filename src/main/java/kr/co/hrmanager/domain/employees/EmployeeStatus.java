package kr.co.hrmanager.domain.employees;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Entity
public class EmployeeStatus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long esId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees empId;
    @Enumerated(EnumType.STRING)
    private EmployeeStatusType esTy;
    private LocalDate createDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeStatus that = (EmployeeStatus) o;
        return Objects.equals(esId, that.esId) && Objects.equals(empId, that.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(esId, empId);
    }
}
