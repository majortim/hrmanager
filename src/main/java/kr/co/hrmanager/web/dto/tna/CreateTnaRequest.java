package kr.co.hrmanager.web.dto.tna;

import kr.co.hrmanager.domain.tna.TnaType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateTnaRequest {
    private Long empId;
    private Long leaveId;
    private TnaType tnaTy;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
}
