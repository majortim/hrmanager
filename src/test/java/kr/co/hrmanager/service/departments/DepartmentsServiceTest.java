package kr.co.hrmanager.service.departments;

import kr.co.hrmanager.web.dto.departments.DepartmentsSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Slf4j
class DepartmentsServiceTest {

    @Autowired
    DepartmentsService departmentsService;

    @Test
    void save() {
        DepartmentsSaveRequest request
                = DepartmentsSaveRequest.builder()
                .deptId(1L)
                .deptName("경영지원팀")
                .build();

        Long savedId = departmentsService.save(request);

        assertEquals(1L, savedId);
    }

}