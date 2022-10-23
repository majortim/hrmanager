package kr.co.hrmanager.domain.pto;

import lombok.Getter;

@Getter
public enum PaidTimeOffType {
    ANNUAL("연차"),
    COMPENSATION("보상휴가");

    private final String type;

    PaidTimeOffType(String status) {
        this.type = status;
    }

}
