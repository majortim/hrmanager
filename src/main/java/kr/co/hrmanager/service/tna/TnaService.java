package kr.co.hrmanager.service.tna;

import kr.co.hrmanager.domain.employees.Employees;
import kr.co.hrmanager.domain.employees.EmployeesRepository;
import kr.co.hrmanager.domain.tna.Tna;
import kr.co.hrmanager.domain.tna.TnaRepository;
import kr.co.hrmanager.web.dto.tna.CreateTnaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TnaService {
    private final TnaRepository tnaRepository;
    private final EmployeesRepository employeesRepository;
    //private final NonWorkingDaysCalendarRepository nonWorkingDaysCalendarRepository;

    public Optional<Tna> findById(Long id) {
        return tnaRepository.findById(id);
    }

    public Tna create(CreateTnaRequest request) {
        LocalDateTime startDt = request.getStartDt();
        LocalDateTime endDt = request.getEndDt();
        //long days = Period.between(startDt.toLocalDate(), endDt.toLocalDate()).getDays();
        //long countNwd = nonWorkingDaysCalendarRepository.countByEnabledAndNwdDateBetween(true, startDt.toLocalDate(), endDt.toLocalDate());
        Employees employee = employeesRepository.findById(request.getEmpId()).orElseThrow();

        Tna tna = Tna.builder()
                .employee(employee)
                .tnaTy(request.getTnaTy())
                .startDt(startDt)
                .endDt(endDt)
                .build();
        return tnaRepository.save(tna);
    }
}
