package io.hurem.web;

import io.hurem.service.departments.DepartmentsService;
import io.hurem.web.dto.DepartmentsSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    @PostMapping("/api/dept/save")
    public Integer save(@RequestBody DepartmentsSaveRequest requestDto){
        return departmentsService.save(requestDto);
    }
}
