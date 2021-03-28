package io.hurem.domain.jobs;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Jobs {
    @Id
    @Column(name = "jobId")
    private Long jobId;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "useYn", columnDefinition = "CHAR", length = 1)
    private String useYn;

    @Column(name = "delYn", columnDefinition = "CHAR", length = 1)
    private String delYn;

    @Builder
    public Jobs(Long jobId, String jobTitle, String useYn, String delYn) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.useYn = useYn;
        this.delYn = delYn;
    }
}
