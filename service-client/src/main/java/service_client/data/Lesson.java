package service_client.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
public class Lesson {
    private Long id;

    private String name;

    private String description;
}
