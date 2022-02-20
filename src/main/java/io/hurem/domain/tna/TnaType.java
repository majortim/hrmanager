package io.hurem.domain.tna;

public enum TnaType {
    DAY("휴가"),
    MORNING("오전 반차"),
    AFTERNOON("오후 반차"),
    MINUTE("시간 지정");

    private final String type;

    TnaType(String status) {
        this.type = status;
    }

}
