package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.request.UserArguments;
import user.request.UserUpdateArguments;
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
    public Result<Boolean> add(@Valid @RequestBody final UserArguments userArgs) {
        return run(() -> userService.add(userArgs));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Boolean> update(@RequestBody @Valid final UserUpdateArguments userArgs) {
        return run(() -> userService.update(userArgs));
    }
}