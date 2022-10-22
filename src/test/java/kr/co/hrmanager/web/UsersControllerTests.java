package kr.co.hrmanager.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.hrmanager.web.dto.users.LoginRequest;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTests {
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

       postLogin(request)
               .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void loginFail() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .username("admin")
                .password("test123!!")
                .build();

        postLogin(request)
                .andExpect(status().is4xxClientError());
    }

    private ResultActions postLogin(LoginRequest request) {

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

            String content = objectMapper.writeValueAsString(request);

            this.mockMvc.perform(
                    post("/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content)
            ).andExpect(status().isCreated());
        });

        log.debug("root cause: {}", ex.getRootCause().getClass());

    }



    @Test
    public void signup() throws Exception {
        SignUpRequest request = SignUpRequest.builder()
                .username("admin")
                .password("test123!")
                .build();

        String content = objectMapper.writeValueAsString(request);
        this.mockMvc.perform(
                post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        ).andExpect(status().isCreated());
    }
}

