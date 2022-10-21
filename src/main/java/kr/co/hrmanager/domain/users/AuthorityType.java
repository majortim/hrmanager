package kr.co.hrmanager.domain.users;

import lombok.Getter;

@Getter
public enum AuthorityType {
    ROLE_USER("일반사용자"),
    ROLE_ADMIN("관리자");

    private final String type;

    AuthorityType(String type) {
        this.type = type;
    }
}
