package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.entity.UserEntity;
import user.request.UserCreation;
import user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Boolean> login(@RequestParam(value = "login") final String login,
                         @RequestParam(value = "password") final String password) {
        return run(() -> userService.login(login, password));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Result<Boolean> logout(final HttpServletRequest request, final HttpServletResponse response) {
        return run(() -> userService.logout(request, response));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<UserEntity> add(@Valid @RequestBody final UserCreation user) {
        return run(() -> userService.add(user));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<UserEntity> update(@RequestBody final UserEntity user) {
        return run(() -> userService.update(user));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<UserEntity> get(@PathVariable final Long id) {
        return run(() -> userService.get(id));
    }
}