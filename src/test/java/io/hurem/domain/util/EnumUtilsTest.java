package io.hurem.domain.util;

import io.hurem.domain.employees.EmployeeStatusType;
import io.hurem.util.EnumUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
//@SpringBootTest
public class EnumUtilsTest {
    @Test
    public void listTest() {
        log.debug("list1: {}", EnumUtils.getEnumList(EmployeeStatusType.class, EmployeeStatusType.ABSENT, EmployeeStatusType.UNKNOWN));
        log.debug("list2: {}", EnumUtils.getEnumList(EmployeeStatusType.class));
    }
}
