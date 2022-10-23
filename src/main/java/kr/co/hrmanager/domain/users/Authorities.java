package kr.co.hrmanager.domain.users;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
@Builder
@Getter
@Entity
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private Users user;
    @Enumerated(EnumType.STRING)
    private AuthorityType authority;

}
