package kr.co.hrmanager.domain.pto;

import lombok.Getter;

@Getter
public enum PaidTimeOffType {
    ANNUAL("연차"),
    OFFICIAL("공가"),
    COMPENSATION("보상휴가"),
    MATERNITY("출산전후휴가"),
    SPOUSAL_MATERNITY("배우자출산휴가"),
    MISCARRIAGE_STILLBIRTH("유산/사산휴가"),
    SUBFERTILITY("난임치료휴가");

    private final String type;

    PaidTimeOffType(String status) {
        this.type = status;
    }

}
