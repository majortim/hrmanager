package kr.co.hrmanager.domain.salaries;

import kr.co.hrmanager.domain.employees.Employees;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class AnnualBasicSalaries {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long absId;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(referencedColumnName  = "emp_id")
    private Employees employee;
    private Long year;
    private Long salary;
    private Long increase;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnualBasicSalaries that = (AnnualBasicSalaries) o;
        return Objects.equals(absId, that.absId) && Objects.equals(employee, that.employee) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(absId, employee, year);
    }
}
