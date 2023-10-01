package kr.co.hrmanager.controller;

import kr.co.hrmanager.domain.nwd.NonWorkingDaysCalendar;
import kr.co.hrmanager.dto.common.PostSuccessResponse;
import kr.co.hrmanager.dto.nwd.CalendarCreateRequest;
import kr.co.hrmanager.dto.nwd.CalendarFindRequest;
import kr.co.hrmanager.service.nwd.NonWorkingDaysCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/nwd")
public class NonWorkingDaysCalendarController {
    private final NonWorkingDaysCalendarService nonWorkingDaysCalendarService;

    @GetMapping
    public List<NonWorkingDaysCalendar> list(@ModelAttribute CalendarFindRequest request) {
        return nonWorkingDaysCalendarService.findAllByYear(request);
    }

    @PostMapping
    public PostSuccessResponse create(@RequestBody CalendarCreateRequest request) {
        return new PostSuccessResponse(String.format("%s", nonWorkingDaysCalendarService.create(request)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        nonWorkingDaysCalendarService.deleteById(id);
    }
}
