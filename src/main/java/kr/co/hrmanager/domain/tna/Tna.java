package kr.co.hrmanager.domain.tna;

import kr.co.hrmanager.domain.employees.Employees;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder(access = AccessLevel.PUBLIC)
@Getter
@Entity
public class Tna {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long tnaId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employees employee;
    private String tnaTy;
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
        return Objects.hash(tnaId, employee);
    }
}
