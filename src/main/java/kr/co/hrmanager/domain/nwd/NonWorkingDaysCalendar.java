package kr.co.hrmanager.domain.nwd;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "non_working_days_calendar")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder
@Getter
public class NonWorkingDaysCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nwdId;

}