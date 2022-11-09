package kr.co.hrmanager.domain.nwd;

import lombok.*;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Table(name = "non_working_days_weekly")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class NonWorkingDaysWeekly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weeklyId;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    private Boolean weeklyHoliday;
    private Boolean paid;
    private LocalDateTime createDt;
    private Boolean enabled;
}