package kr.co.hrmanager.domain.nwd;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "non_working_days_annual")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class NonWorkingDaysAnnual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualId;
    @Enumerated(EnumType.STRING)
    private NonWorkingDaysAnnualType annualTy;
    private Integer month;
    private Integer dayOfMonth;
    private Boolean lunar;
    private Boolean holiday;
    private Boolean paid;
    private LocalDateTime createDt;
    private Boolean enabled;
}