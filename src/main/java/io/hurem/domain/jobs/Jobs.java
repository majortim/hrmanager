package io.hurem.domain.jobs;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Jobs {
    @Id
    private Integer jobId;

    private String jobTitle;

    public void updateJobs(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Builder
    public Jobs(Integer jobId, String jobTitle) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
    }
}
