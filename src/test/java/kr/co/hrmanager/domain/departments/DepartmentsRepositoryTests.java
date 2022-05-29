package kr.co.hrmanager.domain.departments;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DepartmentsRepositoryTests {
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
        int deptId = 10;
        String deptName = "테스트 본문";
        long managerId = 100L;

        departmentsRepository.save(Departments.builder()
                .deptId(deptId)
                .deptName(deptName)
                .managerId(managerId)
                .build());

        //when
        List<Departments> departmentsList = departmentsRepository.findAll();

        logger.debug("list:  {}", departmentsList);

        //then
        Departments departments = departmentsList.get(0);
        assertThat(departments.getDeptId()).isEqualTo(deptId);
        assertThat(departments.getDeptName()).isEqualTo(deptName);
    }
}
