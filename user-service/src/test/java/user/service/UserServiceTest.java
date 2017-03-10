package user.service;

import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import user.AbstractTest;
import service_client.data.request.UserCreation;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Transactional
public class UserServiceTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Test
    public void testAdd() throws Exception {
        val name = "test name";
        val creation = new UserCreation(name, "sdfsdf", "sdf@asfv.ru", "asasf");
        val user = userService.add(creation);
        assertNotNull(user);
        assertThat(user.getLogin(), is(name));
    }

    @Test
    public void testUpdate() throws Exception {
        val loginOld = "test name";
        val loginNew = "test new name";
        val creation = new UserCreation(loginOld, "sdfsdf", "sdf@asfv.ru", "asasf");
        val oldUser = userService.add(creation);
        assertThat(oldUser.getLogin(), is(loginOld));
        oldUser.setLogin(loginNew);
        val newUser = userService.update(oldUser);
        assertThat(newUser.getLogin(), is(loginNew));

    }
}