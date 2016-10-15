package service_client.client;

import lombok.val;
import org.junit.Test;
import service_client.data.request.UserCreation;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserClientTest {
    private UserClient client = new UserClient();

    @Test
    public void testGet() throws Exception {
        val user = client.get(1L);
        assertNotNull(user);
    }

    @Test
    public void testUpdate() throws Exception {
        val user = client.get(1L);
        val updatedName = "testing set name" + new Random().nextInt();
        user.setName(updatedName);
        val updatedUser = client.update(user);
        assertThat(updatedUser.getName(), is(updatedName));
    }

    @Test
    public void testAdd() throws Exception {
        val creation = new UserCreation("Test User" + new Random().nextInt(), "asDsdfasf", "asdf@asf.ru", "sdSdggsd");
        val user = client.add(creation);
        assertThat(creation.name, is(user.getName()));
    }
}