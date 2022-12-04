package kr.co.hrmanager.dto.users;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class MyInfoResponse {
    private String username;
    private String name;
}
