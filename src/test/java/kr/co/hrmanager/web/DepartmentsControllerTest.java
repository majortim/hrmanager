package io.hurem.web;

import io.hurem.domain.departments.Departments;
import io.hurem.domain.departments.DepartmentsRepository;
import io.hurem.web.dto.DepartmentsSaveRequest;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentsControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @AfterEach
    public void tearDown() {
        departmentsRepository.deleteAll();
    }

    @Test
    public void saveTest(){
        int deptId = 1;
        String deptName = "경영지원팀";

        DepartmentsSaveRequest request = DepartmentsSaveRequest.builder()
                .deptId(deptId)
                .deptName(deptName)
                .build();

        String url = "http://localhost:" + port + "/api/dept/save";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Departments> list = departmentsRepository.findAll();
        assertThat(list.get(0).getDeptName()).isEqualTo(deptName);
    }
}
