package com.hackday.controller;

import com.google.gson.Gson;
import com.hackday.Constants;
import com.hackday.requests.TaskArguments;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-configuration.xml")
public class TaskControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGet() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/services/tasks/get?id=2").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id", is(2)))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testGetList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/services/tasks/getList?lessonID=1").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreate() throws Exception {
        Constants.loginRoleAdmin();

        TaskArguments task = new TaskArguments();
        task.name ="Test Name 3";
        task.lessonID = 1L;
        Gson gson = new Gson();
        String json = gson.toJson(task);

        MvcResult result = this.mockMvc.perform(post("/services/tasks/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is(true)))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateUser() throws Exception {
        Constants.loginRoleUser();

        TaskArguments task = new TaskArguments();
        task.name ="Test Error Create";
        task.lessonID = 1L;
        Gson gson = new Gson();
        String json = gson.toJson(task);

        MvcResult result = this.mockMvc.perform(post("/services/tasks/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is5xxServerError())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}