package kr.co.hrmanager.service.users;

import kr.co.hrmanager.domain.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final AuthRepository authRepository;

    public Users findById(String userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    @Transactional
    public void saveUser(Users user) {
        usersRepository.findById(user.getUsername()).ifPresent(u -> { throw new DuplicateKeyException("중복되는 ID 입니다."); });

        Users savedUser = usersRepository.save(user);
        Authorities auth = Authorities.builder()
                .user(savedUser)
                .authority(AuthorityType.ROLE_USER).build();
        authRepository.save(auth);
    }

}
