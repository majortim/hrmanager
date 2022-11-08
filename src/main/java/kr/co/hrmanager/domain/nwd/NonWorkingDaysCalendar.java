package kr.co.hrmanager.domain.nwd;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "non_working_days_calendar")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class NonWorkingDaysCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nwdId;
    @OneToOne
    @JoinColumn(name = "nwd_id", referencedColumnName = "parent_id")
    private NonWorkingDaysCalendar parentNwd;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "annual_id")
    private NonWorkingDaysAnnual annual;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weekly_id")
    private NonWorkingDaysWeekly weekly;
    private LocalDateTime nwdDt;
    private Boolean enabled;


}