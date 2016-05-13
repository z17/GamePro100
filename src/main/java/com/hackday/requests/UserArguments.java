package com.hackday.requests;


import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
public class UserArguments {
    @NotNull
    @Size(min = 3, max = 15)
    public String login;

    @NotNull
    @Size(min = 6, max = 45)
    public String password;

    @NotNull
    public String email;
}
