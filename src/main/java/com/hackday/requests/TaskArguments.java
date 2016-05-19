package com.hackday.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TaskArguments {
    @NotNull
    @Size(min = 1, max = 45)
    public String name;

    @NotNull
    public Long lessonID;
}
