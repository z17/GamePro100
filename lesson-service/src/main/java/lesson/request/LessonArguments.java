package lesson.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LessonArguments {
    @NotNull
    @Size(min = 1, max = 45)
    public String name;

    @NotNull
    public String description;
}
