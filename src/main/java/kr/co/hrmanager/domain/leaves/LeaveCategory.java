package kr.co.hrmanager.domain.leaves;

import lombok.Getter;

@Getter
public enum LeaveCategory {
    DAY("휴가"),
    LEAVE_OF_ABSENCE("휴직");

    private final String category;

    LeaveCategory(String category) {
        this.category = category;
    }
}
