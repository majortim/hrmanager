package kr.co.hrmanager.dto.nwd;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class CalendarDateResponse {
    private final LocalDate nwdDate;
}
