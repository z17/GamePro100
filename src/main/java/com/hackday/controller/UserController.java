package com.hackday.controller;

import com.hackday.constants.Controllers;
import com.hackday.entity.UserEntity;
import com.hackday.requests.UserArguments;
import com.hackday.requests.UserUpdateArguments;
import com.hackday.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.USER)
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = Controllers.LOGIN, method = RequestMethod.POST)
    public boolean login(@RequestParam(value = Controllers.PARAM_LOGIN) final String login,
                         @RequestParam(value = Controllers.PARAM_PASSWORD) final String password) throws IOException {
        return userService.login(login, password);
    }

    @RequestMapping(value = Controllers.LOGOUT, method = RequestMethod.POST)
    public Boolean logout() throws IOException {
        // todo: //
        return false;
    }

    @RequestMapping(value = Controllers.CREATE, method = RequestMethod.POST)
    public boolean create(@RequestBody @Valid final UserArguments userArgs, BindingResult result) {
        result.hasErrors();
        System.out.println(result.getAllErrors());
        System.out.println(result);
        return userService.create(userArgs);
    }

    @RequestMapping(value = Controllers.UPDATE, method = RequestMethod.POST)
    public boolean update(@RequestBody final UserUpdateArguments userArgs) {
        return userService.update(userArgs);
    }
}