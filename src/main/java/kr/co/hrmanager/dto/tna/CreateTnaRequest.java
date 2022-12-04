package kr.co.hrmanager.dto.tna;

import kr.co.hrmanager.domain.leaves.LeaveType;
import kr.co.hrmanager.domain.tna.TnaType;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateTnaRequest {
    private Long empId;
    private String username;
    private TnaType tnaTy;
    private LeaveType leaveTy;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
}
