package kr.co.hrmanager.domain.tna;

import lombok.Getter;

@Getter
public enum TnaType {
    DAY("휴가"),
    MORNING("오전 반차"),
    AFTERNOON("오후 반차"),
    MINUTE("시간 지정"),
    STEP_OUT("외출"),
    OUT_ON_BUSINESS("외근"),
    EARLY_LEAVE("조퇴"),
    ABSENCE_WITHOUT_LEAVE("무단결근"),
    LEAVE_OF_ABSENCE("휴직")
    ;

    private final String type;

    TnaType(String status) {
        this.type = status;
    }

}
