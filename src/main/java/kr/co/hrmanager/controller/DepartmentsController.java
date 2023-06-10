package kr.co.hrmanager.controller;

import kr.co.hrmanager.service.departments.DepartmentsService;
import kr.co.hrmanager.dto.departments.DepartmentsSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    @PostMapping
    public Long save(@RequestBody DepartmentsSaveRequest requestDto){
        return departmentsService.save(requestDto);
    }
}
