package kr.co.hrmanager.domain.integration;

import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.tna.Tna;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.domain.tna.TnaType;
import kr.co.hrmanager.domain.users.Users;
import kr.co.hrmanager.web.dto.tna.CreateTnaRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TnaEmployeesIntegrationTest {

    @Autowired
    TnaRepository tnaRepository;
    @Autowired
    EmployeesRepository employeesRepository;

    @Test
    @Sql({
            "/sql/data/employees.sql"
            , "/sql/data/tna.sql"
    })
    @Transactional(readOnly = true)
    void streamByEmployeeAndTnaTypeListAndDateTime() {
        CreateTnaRequest request = createRequest();

        Stream<Tna> streamTna = createStream(request);
        List<Tna> list = streamTna.collect(Collectors.toList());

        assertFalse(list.isEmpty());
    }

    private CreateTnaRequest createRequest() {
        return CreateTnaRequest.builder()
                .username("admin")
                .startDt(LocalDateTime.of(2022, 1, 1, 1, 1))
                .endDt(LocalDateTime.of(2022, 12, 31, 1, 1, 1))
                .tnaTy(TnaType.ABSENCE_WITHOUT_LEAVE)
                .build();
    }

    private Stream<Tna> createStream(CreateTnaRequest request) {
        LocalDateTime startDt = request.getStartDt();
        LocalDateTime endDt = request.getEndDt();
        TnaType tnaTy = request.getTnaTy();
        String username = request.getUsername();
        Users user = Users.builder().username(username).build();
        Employees employee = employeesRepository.findByUser(user).orElseThrow();
        List<TnaType> filterTnaTypeList = List.of(tnaTy);

        return tnaRepository.streamByEmployeeAndTnaTypeListAndDateTime(employee, filterTnaTypeList, startDt, endDt);
    }
}