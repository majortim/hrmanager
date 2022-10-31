package kr.co.hrmanager.web.controller;

import kr.co.hrmanager.service.pto.PaidTimeOffService;
import kr.co.hrmanager.web.dto.pto.PaidTImeOffCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaidTimeOffController {
    private final PaidTimeOffService paidTimeOffService;

    @PostMapping
    public void create(PaidTImeOffCreateRequest createRequest) {
        paidTimeOffService.create(createRequest);
    }
}
