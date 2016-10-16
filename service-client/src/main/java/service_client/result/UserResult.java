package service_client.result;

import lombok.NoArgsConstructor;
import service_client.data.User;

@NoArgsConstructor
public class UserResult extends Result<User> {
    public UserResult(String message, User data) {
        super(message, data);
    }
}
