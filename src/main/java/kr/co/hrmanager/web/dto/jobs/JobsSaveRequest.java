package kr.co.hrmanager.web.dto.jobs;

import kr.co.hrmanager.domain.departments.Departments;
import kr.co.hrmanager.domain.jobs.Jobs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class JobsSaveRequest {
    private Long jobId;
    private String jobTitle;

    public Jobs toEntity(){
        return Jobs.builder()
                .jobId(jobId)
                .jobTitle(jobTitle)
                .build();
    }
}