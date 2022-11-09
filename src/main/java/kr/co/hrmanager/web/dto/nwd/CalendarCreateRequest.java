package kr.co.hrmanager.web.dto.nwd;

import lombok.*;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CalendarCreateRequest {
    private final Integer baseYear;

    public static CalendarCreateRequest of(Integer baseYear) {
        return new CalendarCreateRequest(baseYear);
    }
}
