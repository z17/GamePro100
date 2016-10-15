package service_client.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private UserRole group;
    private String email;
    private String name;
}