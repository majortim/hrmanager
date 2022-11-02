package kr.co.hrmanager.domain.leaves;

import lombok.Getter;

@Getter
public enum LeaveType {
    ANNUAL("연차"),
    OFFICIAL("공가"),
    COMPENSATION("보상휴가"),
    MENSTRUAL("생리휴가"),
    CONGRATULATIONS_AND_CONDOLENCES("경조사"),
    MATERNITY("출산전후휴가"),
    SPOUSAL_MATERNITY("배우자출산휴가"),
    MISCARRIAGE_STILLBIRTH("유산/사산휴가"),
    SUBFERTILITY("난임치료휴가"),
    SICK("병가"),
    PARENTAL("육아휴직"),
    RECUPERATION("부상/질병 휴직")
    ;

    private final String type;

    LeaveType(String status) {
        this.type = status;
    }

}
