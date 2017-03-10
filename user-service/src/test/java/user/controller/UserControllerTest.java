package user.controller;

import com.google.gson.Gson;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import user.App;
import user.Utils;
import user.entity.UserEntity;
import service_client.data.UserRole;
import service_client.data.request.UserCreation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testAddDuplicateLogin() throws Exception {
        UserCreation user = new UserCreation();
        user.login = "test_user";
        user.password = "qwerty";
        user.email = "ad@asd.ye";
        String json = new Gson().toJson(user);

        this.mockMvc.perform(post("/add")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testAdd() throws Exception {
        UserCreation user = new UserCreation(Utils.randomString(10), "qwerty", "asd@saf.ru", "my name");
        String json = new Gson().toJson(user);

        this.mockMvc.perform(post("/add")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isMap())
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        val user = new UserEntity();
        user.setId(1L);
        user.setName("test");
        user.setPassword("test");
        user.setEmail("test@test.test");
        user.setGroup(UserRole.ROLE_USER);

        String json = new Gson().toJson(user);

        this.mockMvc.perform(post("/update")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isMap())
                .andReturn();
    }
}