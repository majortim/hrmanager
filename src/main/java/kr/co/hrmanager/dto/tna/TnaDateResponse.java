package kr.co.hrmanager.dto.tna;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TnaDateResponse {
    private LocalDateTime startDt;
    private LocalDateTime endDt;
}
