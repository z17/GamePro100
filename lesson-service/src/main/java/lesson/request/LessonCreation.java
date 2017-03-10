package lesson.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class LessonCreation {
    @NotNull
    @Size(min = 1, max = 45)
    public String name;

    @NotNull
    public String description;
}
