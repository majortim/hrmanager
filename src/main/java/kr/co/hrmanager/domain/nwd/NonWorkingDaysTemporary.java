package kr.co.hrmanager.domain.nwd;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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
    @CreatedDate
    private LocalDateTime createDt;
    private Boolean enabled;
}