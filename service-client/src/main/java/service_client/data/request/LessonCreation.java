package service_client.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LessonCreation {
    private String name;

    private String description;
}
