package kr.co.hrmanager.web.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class MyInfoResponse {
    private final String userId;
    private final String name;
}
