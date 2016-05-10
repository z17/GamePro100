package com.hackday.controller;

import com.google.gson.Gson;
import com.hackday.requests.UserArguments;
import com.hackday.special.LoggingUtility;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-configuration.xml")
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testCreate() throws Exception {
        UserArguments user = new UserArguments();
        user.login = "qwerty";
        user.password = "qwerty";
        user.email = "ad@asd.ye";
        Gson gson = new Gson();
        String json = gson.toJson(user);

        ResultActions result = this.mockMvc.perform(post("/services/user/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        LoggingUtility.i(result);
    }
}