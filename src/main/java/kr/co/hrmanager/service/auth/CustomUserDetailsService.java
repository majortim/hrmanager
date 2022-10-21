package kr.co.hrmanager.service.auth;

import kr.co.hrmanager.domain.users.Users;
import kr.co.hrmanager.domain.users.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepository repository;

    public CustomUserDetailsService(UsersRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return repository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
