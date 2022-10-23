package kr.co.hrmanager.service.departments;

import kr.co.hrmanager.domain.departments.DepartmentsRepository;
import kr.co.hrmanager.web.dto.departments.DepartmentsSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DepartmentsService {
    private final DepartmentsRepository departmentsRepository;

    @Transactional
    public Long save(DepartmentsSaveRequest requestDto) {
        return departmentsRepository.save(requestDto.toEntity()).getDeptId();
    }
}
