package kr.co.hrmanager.domain.departments;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder
@Getter
@Entity
public class Departments {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String deptName;

    private Long managerId;
}