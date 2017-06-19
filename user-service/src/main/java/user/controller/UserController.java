package user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service_client.data.User;
import service_client.data.request.UserCreation;
import service_client.result.Result;
import user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static service_client.result.Result.run;

@RestController
@RequestMapping(value = "/rest")
public class UserController {

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
    public Result<User> add(@RequestBody final UserCreation user) {
        return run(() -> userService.add(user));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<User> update(@RequestBody final User user) {
        return run(() -> userService.update(user));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result<User> get(@PathVariable final Long id) {
        return run(() -> userService.get(id));
    }
}