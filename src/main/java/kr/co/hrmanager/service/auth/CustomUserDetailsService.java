package kr.co.hrmanager.service.auth;

import kr.co.hrmanager.domain.users.Authorities;
import kr.co.hrmanager.domain.users.Users;
import kr.co.hrmanager.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
        List<Authorities> authoritiesList = user.getAuthoritiesList();

        if(!CollectionUtils.isEmpty(authoritiesList)) {
            return new CustomUserDetails(user);
        }

        else
            throw new AccessDeniedException("권한이 불충분합니다.");
    }

    static final class CustomUserDetails extends Users implements UserDetails {

        CustomUserDetails(Users user) {
            super(user.getUsername(), user.getPassword(), user.getEnabled(), user.getAuthoritiesList());
        }


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return super.getGrantedAuthorities();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return super.getEnabled();
        }
    }

}
