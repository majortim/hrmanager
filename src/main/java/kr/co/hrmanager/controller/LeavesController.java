package kr.co.hrmanager.controller;

import kr.co.hrmanager.service.leaves.LeavesService;
import kr.co.hrmanager.dto.leaves.CreateAnnualRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/leaves")
public class LeavesController {
    private final LeavesService leavesService;

    @PostMapping
    public void create(CreateAnnualRequest createRequest) {
        leavesService.createAnnual(createRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        leavesService.deleteById(id);
    }
}
