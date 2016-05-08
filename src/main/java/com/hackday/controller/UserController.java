package com.hackday.controller;

import com.hackday.constants.Controllers;
import com.hackday.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.USER)
public class UserController {

    @Autowired
    @Qualifier("authenticationManager")
    AuthenticationManager authenticationManager;

    @RequestMapping(value = Controllers.LOGIN, method = RequestMethod.GET)
    public boolean login(@RequestParam(value = Controllers.PARAM_LOGIN) final String login,
                    @RequestParam(value = Controllers.PARAM_PASSWORD) final String password) throws IOException {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        UserEntity details = new UserEntity();
        details.setLogin(login);
        token.setDetails(details);

        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return auth.isAuthenticated();
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            return false;
        }
    }

//    @RequestMapping(value = Controllers.LOGOUT, method = RequestMethod.GET)
//    public Boolean logout() throws IOException {
//        return true;
//    }
}
