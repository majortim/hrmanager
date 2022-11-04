package kr.co.hrmanager.domain.departments;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DepartmentsRepositoryTest {

    @Autowired
    DepartmentsRepository departmentsRepository;

    @AfterEach
    void cleanup() {
        departmentsRepository.deleteAll();
    }

    @Test
    void saveAndLoad() {
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
