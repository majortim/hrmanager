package kr.co.hrmanager.domain.nwd;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "non_working_days_temporary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class NonWorkingDaysTemporary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tempId;
    @Enumerated(EnumType.STRING)
    private NonWorkingDaysTemporaryType tempTy;
    private String tempNm;
    private Boolean paid;
    private LocalDateTime createDt;
    private Boolean enabled;
}