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
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        user.setGroup(UserRole.ROLE_USER);
        user.setEmail(userArguments.email);
        user.setName(userArguments.name);
        dao.create(user);

        return true;
    }

    public boolean update(UserUpdateArguments userArgs) {
        if (!getCurrentUser().getId().equals(userArgs.getId())) {
            throw new RuntimeException("Access denied");
        }

        final UserEntity user = dao.get(userArgs.id);
        user.setEmail(userArgs.email);
        user.setPassword(encodePassword(userArgs.password));
        user.setName(userArgs.name);
        dao.update(user);

        return true;
    }

    public boolean login(final String login, final String password) {
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        token.setDetails(dao.findByUserName(login));
        try {
            final Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return auth.isAuthenticated();
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean logout(final HttpServletRequest request, final HttpServletResponse response) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null) {
            throw new RuntimeException("Authentication not found");
        }

        new SecurityContextLogoutHandler().logout(request, response, auth);
        return true;
    }

    public UserEntity getCurrentUser() {
        return (UserEntity)SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    private String encodePassword(final String password) {
        return bcryptEncoder.encode(password);
    }
}