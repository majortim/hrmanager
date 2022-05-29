package kr.co.hrmanager.domain.salaries;

import kr.co.hrmanager.domain.employees.Employees;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Entity
public class Salaries {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long salaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;
    private LocalDate salaryDt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "particular_id")
    private SalaryParticulars particular;
    private Long salary;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salaries salaries = (Salaries) o;
        return Objects.equals(salaryId, salaries.salaryId) && Objects.equals(employee, salaries.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryId, employee);
    }
}
