package service_client.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {
    private Long id;
    private Long lessonId;
    private String name;
    private String description;
    private String mapPath;
}
