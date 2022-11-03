package kr.co.hrmanager.web.controller;

import kr.co.hrmanager.service.leaves.LeavesService;
import kr.co.hrmanager.web.dto.leaves.LeavesCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LeavesController {
    private final LeavesService leavesService;

    @PostMapping
    public void create(LeavesCreateRequest createRequest) {
        leavesService.create(createRequest);
    }
}
