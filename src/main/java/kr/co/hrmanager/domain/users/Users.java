package kr.co.hrmanager.domain.users;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Entity
public class Users {
    @Id
    private String username;
    private String password;
    private Boolean enabled;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Authorities> authoritiesList = new ArrayList<>();

    public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
        return this.authoritiesList.stream().map(a -> new SimpleGrantedAuthority(a.getAuthority().name())).collect(Collectors.toSet());
    }

}
