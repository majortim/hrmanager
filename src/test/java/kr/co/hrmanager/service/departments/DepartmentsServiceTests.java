package kr.co.hrmanager.service.departments;

import kr.co.hrmanager.web.dto.DepartmentsSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class DepartmentsServiceTests {

    @Autowired
    DepartmentsService departmentsService;

    @Test
    void save() {
        DepartmentsSaveRequest request
                = DepartmentsSaveRequest.builder()
                .deptId(1L)
                .deptName("경영지원팀")
                .build();

        Long savedCount = departmentsService.save(request);

        assertEquals(1L, savedCount);
    }
}