package user.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service_client.data.User;
import user.entity.UserEntity;
import service_client.data.UserRole;
import user.repository.UserRepository;
import service_client.data.request.UserCreation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class UserService {

    @Autowired
    private GetTokenService getTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public User add(final UserCreation userCreation) {
        if (userRepository.findByLogin(userCreation.login) != null) {
            throw new RuntimeException("login error");
        }

        val user = new UserEntity();
        user.setLogin(userCreation.login);
        user.setPassword(encodePassword(userCreation.password));
        user.setGroup(UserRole.ROLE_USER);
        user.setEmail(userCreation.email);
        user.setName(userCreation.name);
        return userRepository.save(user).toDto();
    }

    public User update(final User user) {
        val userEntity = userRepository.findOne(user.getId());
        userEntity.setLogin(user.getLogin());
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        return userRepository.save(userEntity).toDto();
    }

    public String login(final String login, final String password) {
        UserEntity user = userRepository.findByLogin(login);
        if (user != null && bcryptEncoder.matches(password, user.getPassword())) {
            return getTokenService.getToken(user);
        }
        throw new RuntimeException("error");
    }

    public boolean logout(final HttpServletRequest request, final HttpServletResponse response) {
        throw new UnsupportedOperationException("implement this");
    }

    private String encodePassword(final String password) {
        return bcryptEncoder.encode(password);
    }

    public User get(Long id) {
        return userRepository.findOne(id).toDto();
    }

    public UserEntity getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}