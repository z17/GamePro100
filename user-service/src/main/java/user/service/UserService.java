package user.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.entity.UserEntity;
import user.entity.UserRole;
import user.repository.UserRepository;
import user.request.UserCreation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserEntity add(final UserCreation userCreation) {
        if (userRepository.findByLogin(userCreation.login) != null) {
            throw new RuntimeException("login error");
        }

        val user = new UserEntity();
        user.setLogin(userCreation.login);
        user.setPassword(encodePassword(userCreation.password));
        user.setGroup(UserRole.ROLE_USER);
        user.setEmail(userCreation.email);
        user.setName(userCreation.name);
        return userRepository.save(user);
    }

    public UserEntity update(final UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    // todo: implement!
    public boolean login(final String login, final String password) {
        throw new UnsupportedOperationException("implement this");
    }

    // todo: implement!
    public boolean logout(final HttpServletRequest request, final HttpServletResponse response) {
        throw new UnsupportedOperationException("implement this");
    }

    private String encodePassword(final String password) {
        return bcryptEncoder.encode(password);
    }
}