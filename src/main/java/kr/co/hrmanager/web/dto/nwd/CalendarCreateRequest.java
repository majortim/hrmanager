package kr.co.hrmanager.web.dto.nwd;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CalendarCreateRequest {
    private Integer baseYear;
}
