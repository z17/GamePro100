package com.hackday.controller;

import com.google.gson.Gson;
import com.hackday.entity.TaskEntity;
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
        MvcResult result = this.mockMvc.perform(get("/services/task/get?id=2").accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testGetList() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/services/task/getList?lessonID=1").accept("application/json"))
         //       .andExpect(status().isOk())
         //       .andExpect(jsonPath("$").isArray())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreate() throws Exception {
        TaskArguments task = new TaskArguments();
        task.name ="Test Name 3";
        task.lessonID = 1L;
        Gson gson = new Gson();
        String json = gson.toJson(task);

        MvcResult result = this.mockMvc.perform(post("/services/task/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}