package com.hackday.controller;

import com.hackday.constants.Controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.USER)
public class UserController {

//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @RequestMapping(value = Controllers.LOGIN, method = RequestMethod.GET)
//    public LoginStatus login(@RequestParam(value = Controllers.PARAM_LOGIN) final String login,
//                    @RequestParam(value = Controllers.PARAM_PASSWORD) final String password) throws IOException {
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
//        UserEntity details = new UserEntity();
//        details.setLogin(login);
//        token.setDetails(details);
//
//        try {
//            Authentication auth = authenticationManager.authenticate(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//            return new LoginStatus(auth.isAuthenticated(), auth.getName());
//        } catch (BadCredentialsException e) {
//            return new LoginStatus(false, null);
//        }
//    }
//
//    public class LoginStatus {
//
//        private final boolean loggedIn;
//        private final String username;
//
//        public LoginStatus(boolean loggedIn, String username) {
//            this.loggedIn = loggedIn;
//            this.username = username;
//        }
//
//        public boolean isLoggedIn() {
//            return loggedIn;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//    }
//
//    @RequestMapping(value = Controllers.LOGOUT, method = RequestMethod.GET)
//    public Boolean logout() throws IOException {
//        return true;
//    }
}
