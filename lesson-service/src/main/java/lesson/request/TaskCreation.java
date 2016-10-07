package lesson.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TaskCreation {
    @NotNull
    @Size(min = 1, max = 45)
    public String name;

    @NotNull
    public Long lessonID;

    @NotNull
    public String description;

    @NotNull
    public String mapPath;
}
