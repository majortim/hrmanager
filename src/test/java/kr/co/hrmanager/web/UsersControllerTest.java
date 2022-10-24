package kr.co.hrmanager.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.hrmanager.web.dto.users.LoginRequest;
import kr.co.hrmanager.web.dto.users.LoginResponse;
import kr.co.hrmanager.web.dto.users.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void loginSuccess() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("admin")
                .password("test123!")
                .build();

       requestLogin(request)
               .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void loginFail() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("admin")
                .password("test123!!")
                .build();

        requestLogin(request)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void loginAndGetAuthorities() throws Exception {
        LoginRequest loginRequest = LoginRequest.builder()
                .username("admin")
                .password("test123!")
                .build();

        ResultActions loginResultActions = requestLogin(loginRequest);

        String loginResponseText
                = Optional.of(loginResultActions.andReturn().getResponse().getContentAsString())
                            .orElseThrow(()-> new RuntimeException("로그인 실패.."));

        log.debug("response: {}", loginResponseText);

        LoginResponse loginResponse = Optional.of(objectMapper.readValue(loginResponseText, LoginResponse.class)).orElseThrow();

        ResultActions authoritiesResultActions = this.mockMvc.perform(
                get("/authorities")
                        .header("Authorization", "Bearer " + loginResponse.getToken())
//                            .accept(MediaType.APPLICATION_JSON)
        );

        authoritiesResultActions
                .andExpect(jsonPath("$..authority").isNotEmpty())
                .andExpect(jsonPath("$[0].authority").value("ROLE_ADMIN"))
                .andExpect(status().isOk());

        String authoritiesResponseAsString = Optional.of(authoritiesResultActions.andReturn().getResponse().getContentAsString()).orElseThrow();
        log.debug("authoritiesResponseAsString: {}", authoritiesResponseAsString);
    }

    private ResultActions requestLogin(LoginRequest request) {

        try {
            String content = objectMapper.writeValueAsString(request);

            return this.mockMvc.perform(
                    post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content)
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void signupDuplicateKey() {
        NestedServletException ex = assertThrows(NestedServletException.class, () -> {
            SignUpRequest request = SignUpRequest.builder()
                    .username("admin")
                    .password("test123!")
                    .build();

            signupCommon(request)
                    .andExpect(status().isCreated());
        });

        log.debug("root cause: {}", ex.getRootCause().getClass());

    }

    @Test
    public void signupSuccess() throws Exception {
        SignUpRequest request = SignUpRequest.builder()
                .username("test")
                .password("test123!")
                .build();

        signupCommon(request)
                .andExpect(status().isCreated());
    }

    private ResultActions signupCommon(SignUpRequest request) throws Exception {
        String content = objectMapper.writeValueAsString(request);
        return this.mockMvc.perform(
                post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
    }
}

