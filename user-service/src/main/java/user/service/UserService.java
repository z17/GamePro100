package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.entity.UserEntity;
import user.entity.UserRole;
import user.repository.UserRepository;
import user.request.UserArguments;
import user.request.UserUpdateArguments;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public boolean add(UserArguments userArguments) {
        if (userRepository.findByLogin(userArguments.login) != null) {
            throw new RuntimeException("login error");
        }

        UserEntity user = new UserEntity();
        user.setLogin(userArguments.login);
        user.setPassword(encodePassword(userArguments.password));
        user.setGroup(UserRole.ROLE_USER);
        user.setEmail(userArguments.email);
        user.setName(userArguments.name);
        userRepository.save(user);

        return true;
    }

    public boolean update(UserUpdateArguments userArgs) {
        final UserEntity user = userRepository.findOne(userArgs.id);
        user.setEmail(userArgs.email);
        user.setPassword(encodePassword(userArgs.password));
        user.setName(userArgs.name);
        userRepository.save(user);

        return true;
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