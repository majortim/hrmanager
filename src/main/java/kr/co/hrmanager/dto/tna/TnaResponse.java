package kr.co.hrmanager.dto.tna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class TnaResponse {
    private Long tnaId;
    private String empName;
    private Integer baseYear;
    private String tnaTy;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
}
