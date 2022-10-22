package kr.co.hrmanager.web.dto.users;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class SignUpRequest {
    @NotNull(message = "ID를 입력해주세요.")
    @Size(max = 15, message = "ID는 15자 이내여아합니다.")
    @Pattern(regexp = "^[\\w]+$", message = "ID는 영문, 숫자 조합으로 해주세요.")
    private String username;
    @NotNull(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 90, message = "비밀번호는 8자 이상 90자 이내여야 합니다.")
    private String password;
}
