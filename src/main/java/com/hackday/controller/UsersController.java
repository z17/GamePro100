package com.hackday.controller;

import com.hackday.constants.Controllers;
import com.hackday.requests.UserArguments;
import com.hackday.requests.UserUpdateArguments;
import com.hackday.services.UsersService;
import com.hackday.special.LoggingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.USERS)
public class UsersController extends AbstractController {

    @Autowired
    private UsersService userService;

    @Secured("ROLE_ANONYMOUS")
    @RequestMapping(value = Controllers.LOGIN, method = RequestMethod.GET)
    public Result<Boolean> login(@RequestParam(value = Controllers.PARAM_LOGIN) final String login,
                         @RequestParam(value = Controllers.PARAM_PASSWORD) final String password) {
        return run(() -> userService.login(login, password));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Controllers.LOGOUT, method = RequestMethod.GET)
    public Result<Boolean> logout(final HttpServletRequest request, final HttpServletResponse response) {
        return run(() -> userService.logout(request, response));
    }

    @Secured("ROLE_ANONYMOUS")
    @RequestMapping(value = Controllers.CREATE, method = RequestMethod.POST)
    public Result<Boolean> create(@Valid @RequestBody final UserArguments userArgs) {
        return run(() -> userService.create(userArgs));
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = Controllers.UPDATE, method = RequestMethod.POST)
    public Result<Boolean> update(@RequestBody @Valid final UserUpdateArguments userArgs) {
        return run(() -> userService.update(userArgs));
    }
}