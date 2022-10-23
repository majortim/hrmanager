package kr.co.hrmanager.domain.jobs;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder(access = AccessLevel.PUBLIC)
@Getter
@Entity
public class Jobs {
    @Id
    private Long jobId;

    private String jobTitle;

    public void updateJobs(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
