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
    Integer month;
    Integer dayOfMonth;
    Boolean lunar;
    Boolean holiday;
    Boolean paid;
    LocalDateTime createDt;
    Boolean enabled;
}