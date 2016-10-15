package service_client.client;

import service_client.data.User;
import service_client.data.request.UserCreation;
import service_client.result.UserResult;

public class UserClient extends Client {
    private static final String SERVICE_PATH = "/user-service";

    UserClient() {
        super(SERVICE_PATH);
    }

    public User get(final Long id) {
        return get("/get/" + id, UserResult.class).getData();
    }

    public User update(final User user) {
        return post("/update", user, UserResult.class).getData();
    }

    public User add(final UserCreation user) {
        return post("/add", user, UserResult.class).getData();
    }
}