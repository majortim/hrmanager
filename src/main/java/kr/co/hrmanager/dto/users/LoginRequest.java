package kr.co.hrmanager.dto.users;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class LoginRequest {
    @NotNull(message = "ID를 입력해주세요.")
    private String username;
    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;
}
