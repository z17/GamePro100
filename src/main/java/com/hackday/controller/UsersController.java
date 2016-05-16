package com.hackday.controller;

import com.hackday.constants.Controllers;
import com.hackday.requests.UserArguments;
import com.hackday.requests.UserUpdateArguments;
import com.hackday.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.USERS)
public class UsersController extends AbstractController {

    @Autowired
    private UsersService userService;

    @RequestMapping(value = Controllers.LOGIN, method = RequestMethod.POST)
    public Result<Boolean> login(@RequestParam(value = Controllers.PARAM_LOGIN) final String login,
                         @RequestParam(value = Controllers.PARAM_PASSWORD) final String password) {
        return run(() -> userService.login(login, password));
    }

    @RequestMapping(value = Controllers.LOGOUT, method = RequestMethod.POST)
    public Result<Boolean> logout() {
        // todo: //
        return run(() -> true);
    }

    @RequestMapping(value = Controllers.CREATE, method = RequestMethod.POST)
    public Result<Boolean> create(@Valid @RequestBody final UserArguments userArgs) {
        return run(() -> userService.create(userArgs));
    }

    @RequestMapping(value = Controllers.UPDATE, method = RequestMethod.POST)
    public Result<Boolean> update(@RequestBody @Valid final UserUpdateArguments userArgs) {
        return run(() -> userService.update(userArgs));
    }
}