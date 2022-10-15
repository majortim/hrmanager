package kr.co.hrmanager.web;

import kr.co.hrmanager.service.departments.DepartmentsService;
import kr.co.hrmanager.web.dto.DepartmentsSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    @PostMapping("/api/dept/save")
    public Long save(@RequestBody DepartmentsSaveRequest requestDto){
        return departmentsService.save(requestDto);
    }
}
