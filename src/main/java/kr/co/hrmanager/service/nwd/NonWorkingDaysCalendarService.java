package kr.co.hrmanager.service.nwd;

import kr.co.hrmanager.domain.nwd.*;
import kr.co.hrmanager.dto.nwd.CalendarCreateRequest;
import kr.co.hrmanager.dto.nwd.CalendarFindRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.time4j.PlainDate;
import net.time4j.calendar.EastAsianMonth;
import net.time4j.calendar.EastAsianYear;
import net.time4j.calendar.KoreanCalendar;
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

        for(NonWorkingDaysAnnual itemAnnual : listAnnual) {
            LocalDate nwdDate;

            //음력일경우 양력으로 변환
            if(itemAnnual.getLunar()) {
                KoreanCalendar lunarDate =
                        KoreanCalendar.of(EastAsianYear.forGregorian(baseYear), EastAsianMonth.valueOf(itemAnnual.getMonth()), itemAnnual.getDayOfMonth());
                nwdDate = lunarDate.transform(PlainDate.axis()).toTemporalAccessor();
            }
            else {
                nwdDate = LocalDate.of(baseYear, itemAnnual.getMonth(), itemAnnual.getDayOfMonth());
            }

            NonWorkingDaysCalendar calendarDate = NonWorkingDaysCalendar.builder()
                    .annual(itemAnnual)
                    .nwdDate(nwdDate)
                    .enabled(true)
                    .build();

            listCalendar.add(calendarDate);

            int cnt = itemAnnual.getAnnualCnt();
            int offset = itemAnnual.getAnnualOffset();

            //추석, 설날처럼 휴일이 1일보다 많이 지정되어 있는 경우
            if(cnt > 1) {
                for(int i = 0; i < cnt; i++) {
                    int plusNumber = offset + i;
                    if(plusNumber != 0) {
                        LocalDate additionalDate = nwdDate.plusDays(plusNumber);
                        NonWorkingDaysCalendar childCalendarDate = NonWorkingDaysCalendar.builder()
                                .parentNwd(calendarDate)
                                .annual(itemAnnual)
                                .nwdDate(additionalDate)
                                .enabled(true)
                                .build();

                        listCalendar.add(childCalendarDate);
                    }
                }
            }
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

        log.debug("listCalendar size: {}", listCalendar.size());

        if(listCalendar.isEmpty())
            return false;

        repository.saveAll(listCalendar);
        return true;
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
