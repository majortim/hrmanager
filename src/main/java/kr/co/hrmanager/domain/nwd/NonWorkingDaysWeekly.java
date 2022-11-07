package kr.co.hrmanager.domain.nwd;

import lombok.*;

import javax.persistence.*;
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
    Integer dayOfWeek;
    Boolean weeklyHoliday;
    Boolean paid;
    LocalDateTime createDt;
    Boolean enabled;
}