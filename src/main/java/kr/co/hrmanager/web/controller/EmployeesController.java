package kr.co.hrmanager.web.controller;

import kr.co.hrmanager.service.employees.EmployeesService;
import kr.co.hrmanager.web.dto.common.PostSuccessResponse;
import kr.co.hrmanager.web.dto.employees.CreateEmployeeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class EmployeesController {

    private final EmployeesService employeesService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public PostSuccessResponse create(@Valid @RequestBody CreateEmployeeRequest request) {

        employeesService.create(request);

        return new PostSuccessResponse("회원가입이 완료됐습니다.");
    }
}
