package kr.co.hrmanager.service.nwd;

import kr.co.hrmanager.domain.nwd.*;
import kr.co.hrmanager.web.dto.nwd.CalendarCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NonWorkingDaysCalendarService {
    private final NonWorkingDaysCalendarRepository repository;
    private final NonWorkingDaysAnnualRepository annualRepository;
    private final NonWorkingDaysWeeklyRepository weeklyRepository;
    private final NonWorkingDaysTemporaryRepository temporaryRepository;

    @Transactional
    public void create(CalendarCreateRequest request) {
        List<NonWorkingDaysCalendar> list = new ArrayList<>();
        repository.saveAll(list);
    }
}
