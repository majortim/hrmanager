package kr.co.hrmanager.domain.salaries;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED, staticName = "of")
@Entity
public class SalaryParticulars {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long particularId;
    private String particular;
    private SalaryType salaryTy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryParticulars that = (SalaryParticulars) o;
        return Objects.equals(particularId, that.particularId) && Objects.equals(particular, that.particular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(particularId, particular);
    }
}