package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service_client.data.User;
import service_client.data.request.UserCreation;
import service_client.result.Result;
import service_client.security.TokenData;
import user.service.UserService;

import static service_client.result.Result.run;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_ANONYMOUS")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<String> login(@RequestParam(value = "login") final String login,
                                 @RequestParam(value = "password") final String password) {
        return run(() -> userService.login(login, password));
    }

    @Secured("ROLE_ANONYMOUS")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<User> add(@RequestBody final UserCreation user) {
        return run(() -> userService.add(user));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<User> update(@RequestBody final User user) {
        return run(() -> userService.update(user));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<User> get(@PathVariable final Long id) {
        return run(() -> userService.get(id));
    }
}