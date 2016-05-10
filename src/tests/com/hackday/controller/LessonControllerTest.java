package com.hackday.controller;

import com.google.gson.Gson;
import com.hackday.entity.LessonEntity;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-configuration.xml")
public class LessonControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGet() throws Exception {
        MvcResult result1 = this.mockMvc.perform(post("/services/lesson/get?id=1")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andReturn();
        System.out.println(result1.getResponse().getContentAsString());

        MvcResult result2 = this.mockMvc.perform(post("/services/lesson/get?id=3")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andReturn();
        System.out.println(result2.getResponse().getContentAsString());
    }

    @Test
    public void testGetList() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/services/lesson/getList")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testCreate() throws Exception {
        LessonEntity lesson = new LessonEntity();
        lesson.setName("Test Name 2");
        Gson gson = new Gson();
        String json = gson.toJson(lesson);

        MvcResult result = this.mockMvc.perform(post("/services/lesson/create")
                .accept("application/json")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)))
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}