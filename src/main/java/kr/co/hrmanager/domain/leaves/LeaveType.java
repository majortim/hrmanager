package kr.co.hrmanager.domain.leaves;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.hrmanager.domain.leaves.LeaveCategory.*;

@Getter
public enum LeaveType {
    ANNUAL("연차", DAY),
    OFFICIAL("공가", DAY),
    COMPENSATION("보상휴가", DAY),
    MENSTRUAL("생리휴가", DAY),
    CONGRATULATIONS_AND_CONDOLENCES("경조사", DAY),
    MATERNITY("출산전후휴가", DAY),
    SPOUSAL_MATERNITY("배우자출산휴가", DAY),
    MISCARRIAGE_STILLBIRTH("유산·사산휴가", DAY),
    SUBFERTILITY("난임치료휴가", DAY),
    SICK("병가", DAY),
    PARENTAL("육아휴직", LEAVE_OF_ABSENCE),
    RECUPERATION("부상·질병휴직", LEAVE_OF_ABSENCE),
    PERSONAL("기타 개인사유로 인한 휴직", LEAVE_OF_ABSENCE)
    ;

    private final String type;
    private final LeaveCategory category;

    LeaveType(String type, LeaveCategory category) {
        this.type = type;
        this.category = category;
    }

    public static List<LeaveType> listAllByCategory(LeaveCategory category) {
        return Arrays.stream(values()).filter(type -> type.getCategory().equals(category)).collect(Collectors.toList());
    }

}
