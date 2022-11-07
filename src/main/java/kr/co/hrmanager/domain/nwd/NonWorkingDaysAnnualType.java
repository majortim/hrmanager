package kr.co.hrmanager.domain.nwd;

import lombok.Getter;

@Getter
public enum NonWorkingDaysAnnualType {
    STATUTORY_HOLIDAY("법정휴일"),
    PUBLIC_HOLIDAY("공휴일"),
    CONTRACTUAL_HOLIDAY("약정휴일");
    private final String type;

    NonWorkingDaysAnnualType(String type) {
        this.type = type;
    }
}
