package com.hackday.controller;

import com.google.gson.Gson;
import com.hackday.AppConfig;
import com.hackday.Constants;
import com.hackday.requests.UserArguments;
import com.hackday.requests.UserUpdateArguments;
import com.hackday.special.LoggingUtility;
import com.mysql.cj.jdbc.Driver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppConfig.class)
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
    public void testCreateDuplicateLogin() throws Exception {
        Constants.loginRoleAnonymous();

        UserArguments user = new UserArguments();
        user.login = "qwerty";
        user.password = "qwerty";
        user.email = "ad@asd.ye";
        String json = new Gson().toJson(user);

        this.mockMvc.perform(post("/services/users/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testCreateFromAlreadyLogin() throws Exception {
        Constants.loginRoleUser();

        UserArguments user = new UserArguments();
        user.login = "qwerty23132";
        user.password = "qwerty";
        user.email = "ad@asd.ye";
        String json = new Gson().toJson(user);

        this.mockMvc.perform(post("/services/users/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testCreate() throws Exception {
        Constants.loginRoleAnonymous();

        UserArguments user = new UserArguments(Constants.randomString(10), "qwerty", "asd@saf.ru", "my name");
        String json = new Gson().toJson(user);

        MvcResult result = this.mockMvc.perform(post("/services/users/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is(true)))
                .andReturn();

        LoggingUtility.i(result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdate() throws Exception {
        Constants.loginRoleUser();
        UserUpdateArguments user = new UserUpdateArguments(Constants.ROLE_USER_ID, Constants.ROLE_USER_PASSWORD, "asv@sgvd.ru", "my name");

        String json = new Gson().toJson(user);

        MvcResult result = this.mockMvc.perform(post("/services/users/update")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is(true)))
                .andReturn();
        LoggingUtility.i(result.getResponse().getContentAsString());
    }

    @Test
    public void testUpdateAnon() throws Exception {
        Constants.loginRoleAnonymous();
        UserUpdateArguments user = new UserUpdateArguments(Constants.ROLE_USER_ID, Constants.ROLE_USER_PASSWORD, "asv@sgvd.ru", "my name");

        String json = new Gson().toJson(user);

        MvcResult result = this.mockMvc.perform(post("/services/users/update")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

}