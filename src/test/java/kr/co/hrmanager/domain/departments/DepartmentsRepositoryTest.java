package kr.co.hrmanager.domain.departments;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DepartmentsRepositoryTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DepartmentsRepository departmentsRepository;

    @AfterEach
    public void cleanup() {
        departmentsRepository.deleteAll();
    }

    @Test
    public void saveAndLoad() {
        //given
        Long deptId = 10L;
        String deptName = "테스트 부서명";
        Long managerId = 100L;

        //when

        Departments savedDepartment = departmentsRepository.save(Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .managerId(managerId)
                .build());

        //then
        assertThat(savedDepartment.getDeptId()).isEqualTo(deptId);
        assertThat(savedDepartment.getDeptName()).isEqualTo(deptName);
    }
}
