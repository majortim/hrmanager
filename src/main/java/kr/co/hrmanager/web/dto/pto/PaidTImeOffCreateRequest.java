package kr.co.hrmanager.web.dto.pto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class PaidTImeOffCreateRequest {
    private Long empId;
}
