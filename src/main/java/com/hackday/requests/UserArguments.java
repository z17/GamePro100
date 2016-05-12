package com.hackday.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserArguments {
    public String login;

    @NotNull
    @Size(min = 16, max = 45)
    public String password;
    public String email;
}
