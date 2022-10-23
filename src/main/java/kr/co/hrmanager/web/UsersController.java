package kr.co.hrmanager.web;


import kr.co.hrmanager.domain.users.Users;
import kr.co.hrmanager.service.auth.JwtService;
import kr.co.hrmanager.service.users.UsersService;
import kr.co.hrmanager.web.dto.common.PostSuccessResponse;
import kr.co.hrmanager.web.dto.users.LoginRequest;
import kr.co.hrmanager.web.dto.users.LoginResponse;
import kr.co.hrmanager.web.dto.users.SignUpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;


@Slf4j
@RequiredArgsConstructor
@RestController
public class UsersController {
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public PostSuccessResponse signup(@Valid @RequestBody SignUpRequest request) {

        Users user = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        usersService.saveUser(user);

        return new PostSuccessResponse("회원가입이 완료됐습니다.");
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        try {

            //SecurityContext context = SecurityContextHolder.createEmptyContext();

            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            log.debug("authentication: {}", authentication);
            log.debug("token: {}", authentication != null ? jwtService.getToken(authentication) : "");

            return new LoginResponse(jwtService.getToken(Objects.requireNonNull(authentication)));
        } catch (AuthenticationException e) {
            log.error("인증 실패", e);
            throw e;
        }
    }
}
