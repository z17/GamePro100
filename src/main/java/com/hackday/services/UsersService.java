package com.hackday.services;

import com.hackday.dao.UsersDao;
import com.hackday.entity.UserEntity;
import com.hackday.entity.UserRole;
import com.hackday.requests.UserArguments;
import com.hackday.requests.UserUpdateArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UsersService {
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersDao dao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public boolean create(UserArguments userArguments) {
        if (dao.findByUserName(userArguments.login) != null) {
            throw new RuntimeException("login error");
        }

        UserEntity user = new UserEntity();
        user.setLogin(userArguments.login);
        user.setPassword(encodePassword(userArguments.password));
        user.setGroup(UserRole.USER);
        user.setEmail(userArguments.email);
        dao.create(user);

        return true;
    }

    public boolean update(UserUpdateArguments userArgs) {
        UserEntity user = dao.get(userArgs.id);
        user.setEmail(userArgs.email);
        user.setPassword(encodePassword(userArgs.password));
        dao.update(user);

        return true;
    }

    public boolean login(final String login, final String password) {
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        final UserEntity details = new UserEntity();
        details.setLogin(login);
        token.setDetails(details);
        try {
            final Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return auth.isAuthenticated();
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            return false;
        }
    }

    private String encodePassword(final String password) {
        return bcryptEncoder.encode(password);
    }
}