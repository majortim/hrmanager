package kr.co.hrmanager.web.dto.leaves;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateAnnualRequest {
    private Long empId;
    private Integer baseYear;
}
