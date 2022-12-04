package kr.co.hrmanager.service.tna;

import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.tna.Tna;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.domain.users.Users;
import kr.co.hrmanager.dto.tna.CreateTnaRequest;
import kr.co.hrmanager.dto.tna.FindTnaCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TnaService {
    private final TnaRepository tnaRepository;
    private final EmployeesRepository employeesRepository;

    public Optional<Tna> findById(Long id) {
        return tnaRepository.findById(id);
    }

    public Tna create(CreateTnaRequest request) {
        LocalDateTime startDt = request.getStartDt();
        LocalDateTime endDt = request.getEndDt();
        String username = request.getUsername();
        Users user = Users.builder().username(username).build();
        //long days = Period.between(startDt.toLocalDate(), endDt.toLocalDate()).getDays();
        //long countNwd = nonWorkingDaysCalendarRepository.countByEnabledAndNwdDateBetween(true, startDt.toLocalDate(), endDt.toLocalDate());
        Employees employee = employeesRepository.findByUser(user).orElseThrow();

        Tna tna = Tna.builder()
                .employee(employee)
                .tnaTy(request.getTnaTy())
                .startDt(startDt)
                .endDt(endDt)
                .build();
        return tnaRepository.save(tna);
    }

    public Set<LocalDate> toSetAllDates(FindTnaCondition condition) {
        final LocalDateTime startDt = condition.getTargetStartDt();
        final LocalDateTime endDt = condition.getTargetEndDt();

        Stream<Tna> streamTna =
                tnaRepository.streamByCondition(condition);

        return streamTna.flatMap(tna -> {
            LocalDate tnaStartDate = tna.getStartDt().toLocalDate();
            LocalDate tnaEndDate = tna.getEndDt().toLocalDate();
            LocalDate requestStartDate = startDt.toLocalDate();
            LocalDate requestEndDate = endDt.toLocalDate();

            LocalDate startDate = tnaStartDate.isAfter(requestStartDate) ? tnaStartDate : requestStartDate;
            LocalDate endDate = tnaEndDate.isBefore(requestEndDate) ? tnaEndDate : requestEndDate;

            return startDate.datesUntil(endDate.plusDays(1));
        })
                .collect(Collectors.toSet());
    }
}
