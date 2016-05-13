package com.hackday.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUpdateArguments {

    @NotNull
    public Long id;

    @NotNull
    @Size(min = 6, max = 45)
    public String password;

    @NotNull
    public String email;
}
