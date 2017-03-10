package result.controller;

import com.google.gson.Gson;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import result.App;
import result.entity.AnswerEntity;
import result.service.ResultService;
import service_client.data.TaskResult;
import service_client.data.request.SubmitRequest;

import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@Transactional
public class ResultControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(resultController).build();
    }

    @Test
    public void testSubmit() throws Exception {
        val submit = new SubmitRequest(1L, "asdasd");

        when(resultService.submit(any(SubmitRequest.class))).thenReturn(new TaskResult(TaskResult.Status.COMPILE_ERROR, "asdasd"));
        mockMvc.perform(post("/submit/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(submit)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isMap())
                .andReturn();

        verify(resultService).submit(any(SubmitRequest.class));

    }

    @Test
    public void testGetByTask() throws Exception {
        val answer = new AnswerEntity();
        answer.setId(1L);
        when(resultService.getByTask(anyLong())).thenReturn(Collections.singletonList(answer));

        MvcResult mvcResult = mockMvc.perform(get("/getByTask/1")
                .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

        verify(resultService).getByTask(anyLong());
    }
}