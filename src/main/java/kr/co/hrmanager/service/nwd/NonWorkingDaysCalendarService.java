package kr.co.hrmanager.service.nwd;

import kr.co.hrmanager.domain.nwd.*;
import kr.co.hrmanager.web.dto.nwd.CalendarCreateRequest;
import kr.co.hrmanager.web.dto.nwd.CalendarFindRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.TemporalAdjusters.next;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class NonWorkingDaysCalendarService {
    private final NonWorkingDaysCalendarRepository repository;
    private final NonWorkingDaysAnnualRepository annualRepository;
    private final NonWorkingDaysWeeklyRepository weeklyRepository;

    public List<NonWorkingDaysCalendar> findAllByYear(CalendarFindRequest request) {
        Integer year = request.getYear();
        Stream<NonWorkingDaysCalendar> stream = repository.streamByYear(year);

        return stream.collect(Collectors.toList());
    }

    @Transactional
    public boolean create(CalendarCreateRequest request) {
        Integer baseYear = request.getBaseYear();

        List<NonWorkingDaysCalendar> listCalendar = new ArrayList<>();
        List<NonWorkingDaysAnnual> listAnnual = annualRepository.findAllByEnabled(true);
        List<NonWorkingDaysWeekly> listWeekly = weeklyRepository.findAllByEnabled(true);

        //TODO cnt, offset에 대한 처리
        for(NonWorkingDaysAnnual itemAnnual : listAnnual){
            LocalDate nwdDate = LocalDate.of(baseYear, itemAnnual.getMonth(), itemAnnual.getDayOfMonth());

            NonWorkingDaysCalendar calendarDate = NonWorkingDaysCalendar.builder()
                    .annual(itemAnnual)
                    .nwdDate(nwdDate)
                    .enabled(true)
                    .build();

            listCalendar.add(calendarDate);
        }

        for(NonWorkingDaysWeekly itemWeekly : listWeekly) {
            DayOfWeek dow = itemWeekly.getDayOfWeek();

            LocalDate firstDayOfYear = LocalDate.ofYearDay(baseYear, 1);
            LocalDate nwdDate = firstDayOfYear.with(next(dow));


            while(nwdDate.getYear() == baseYear) {
                NonWorkingDaysCalendar calendarDate = NonWorkingDaysCalendar.builder()
                        .weekly(itemWeekly)
                        .nwdDate(nwdDate)
                        .enabled(true)
                        .build();
                listCalendar.add(calendarDate);

                nwdDate = nwdDate.with(next(dow));
            }
        }

        log.debug("listAnnual size: {}", listAnnual.size());
        log.debug("listWeekly size: {}", listWeekly.size());
        log.debug("listCalendar size: {}", listCalendar.size());

        if(listCalendar.isEmpty())
            return false;

        repository.saveAll(listCalendar);
        return true;
    }
}
