package service_client.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskCreation {
    private String name;
    private Long lessonID;
    private String description;
    private String mapPath;
}
