package kr.co.hrmanager.web.dto.nwd;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CalendarFindRequest {
    private final Integer year;

    public static CalendarFindRequest of(Integer year) {
        return new CalendarFindRequest(year);
    }
}
