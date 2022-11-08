package kr.co.hrmanager.domain.nwd;

import lombok.Getter;

@Getter
public enum NonWorkingDaysTemporaryType {
    ALTERNATIVE("대체휴일"),
    ELECTION("선거일");
    private final String type;

    NonWorkingDaysTemporaryType(String type) {
        this.type = type;
    }
}