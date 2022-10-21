package kr.co.hrmanager.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.hrmanager.domain.departments.Departments;
import kr.co.hrmanager.domain.departments.DepartmentsRepository;
import kr.co.hrmanager.web.dto.DepartmentsSaveRequest;
import kr.co.hrmanager.web.dto.users.LoginRequest;
import kr.co.hrmanager.web.dto.users.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class DepartmentsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @AfterEach
    public void tearDown() {
        departmentsRepository.deleteAll();
    }


    @Test
    public void saveTest(){
        Long deptId = 1L;
        String deptName = "경영지원팀";

        LoginRequest loginRequest = LoginRequest.builder()
                .username("admin")
                .password("test123!")
                .build();

        DepartmentsSaveRequest request = DepartmentsSaveRequest.builder()
                .deptId(deptId)
                .deptName(deptName)
                .build();

        try {
            String loginRequestAsString = objectMapper.writeValueAsString(loginRequest);
            String content = objectMapper.writeValueAsString(request);

            String responseText = this.mockMvc.perform(
                    post("/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(loginRequestAsString)
            ).andReturn().getResponse().getContentAsString();

            log.debug("response: {}", responseText);

            LoginResponse loginResponse = Optional.of(objectMapper.readValue(responseText, LoginResponse.class)).orElseThrow();

            ResultActions actions = this.mockMvc.perform(
                    post("/api/dept/save")
                            .header("Authorization", "Bearer " + loginResponse.getToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(content)
//                            .accept(MediaType.APPLICATION_JSON)
            );

            actions.andExpect(status().isOk())
                    .andExpect(content().string("1"));

            List<Departments> list = departmentsRepository.findAll();
            assertThat(list.get(0).getDeptName()).isEqualTo(deptName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
