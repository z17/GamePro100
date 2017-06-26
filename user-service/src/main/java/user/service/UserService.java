package user.service;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service_client.data.User;
import service_client.security.TokenData;
import user.entity.UserEntity;
import service_client.data.UserRole;
import user.repository.UserRepository;
import service_client.data.request.UserCreation;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Value("${token.alive}")
    private int tokenDaysAlive;

    @Value("${token.key}")
    private String key;

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
            return getToken(user);
        }
        throw new RuntimeException("Error");
    }

    private String encodePassword(final String password) {
        return bcryptEncoder.encode(password);
    }

    public User get(Long id) {
        return userRepository.findOne(id).toDto();
    }

    public UserEntity getByLogin(final String login) {
        return userRepository.findByLogin(login);
    }

    private String getToken(final UserEntity user) {
        final Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(TokenData.ID.getValue(), user.getId());
        tokenData.put(TokenData.LOGIN.getValue(), user.getLogin());
        tokenData.put(TokenData.GROUP.getValue(), user.getGroup());
        tokenData.put(TokenData.CREATE_DATE.getValue(), new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, tokenDaysAlive);
        tokenData.put(TokenData.EXPIRATION_DATE.getValue(), calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        return jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
    }

}