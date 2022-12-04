package kr.co.hrmanager.dto.leaves;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateAnnualRequest {
    private String username;
    private Integer baseYear;
}
