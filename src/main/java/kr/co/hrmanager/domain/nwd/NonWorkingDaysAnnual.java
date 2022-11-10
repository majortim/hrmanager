package kr.co.hrmanager.domain.nwd;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
    private String annualNm;
    private Integer annualOffset;
    private Integer annualCnt;
    private Integer month;
    private Integer dayOfMonth;
    private Boolean lunar;
    private Boolean holiday;
    private Boolean paid;
    @CreatedDate
    private LocalDateTime createDt;
    private Boolean enabled;
}