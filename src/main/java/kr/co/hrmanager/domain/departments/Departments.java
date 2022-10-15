package kr.co.hrmanager.domain.departments;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Entity
public class Departments {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String deptName;

    private Long managerId;
}