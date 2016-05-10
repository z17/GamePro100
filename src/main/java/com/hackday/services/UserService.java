package com.hackday.services;

import com.hackday.dao.UserDao;
import com.hackday.entity.UserEntity;
import com.hackday.entity.UserRole;
import com.hackday.requests.UserArguments;
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
public class UserService {
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDao dao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public boolean create(UserArguments userArguments) {
        UserEntity user = new UserEntity();
        user.setLogin(userArguments.login);
        user.setPassword(bcryptEncoder.encode(userArguments.password));
        user.setGroup(UserRole.USER);
        dao.create(user);

        return true;
    }

    public boolean update(UserEntity user) {
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
}