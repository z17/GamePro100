package result.service;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import result.entity.AnswerEntity;
import result.repository.AnswerRepository;
import service_client.client.ExecutorClient;
import service_client.client.TaskClient;
import service_client.client.UserClient;
import service_client.data.Task;
import service_client.data.TaskResult;
import service_client.data.User;
import service_client.data.UserRole;
import service_client.data.request.SubmitRequest;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

public class ResultServiceTest {

    @InjectMocks
    private ResultService resultService;

    @Mock
    private TaskClient taskClient;

    @Mock
    private UserClient userClient;

    @Mock
    private ExecutorClient executorClient;

    @Mock
    private AnswerRepository answerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testSubmit() throws Exception {
        val submit = new SubmitRequest(1L, "my code");
        val executorResponse = new TaskResult(TaskResult.Status.COMPILE_ERROR, "abra cadabtra");

        when(userClient.get(anyLong())).thenReturn(new User(1L, "asd", UserRole.ROLE_ADMIN, "asd", "sdfdf"));
        when(taskClient.get(anyLong())).thenReturn(new Task(1L, 1L, "asdasd", "asdasd", "asfasf"));
        when(executorClient.submit(submit)).thenReturn(executorResponse);
        when(answerRepository.save(any(AnswerEntity.class))).thenReturn(new AnswerEntity());

        val result = resultService.submit(submit);

        assertEquals(result, executorResponse);
    }



    @Test
    public void testGetByTask() throws Exception {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setId(1L);
        answerEntity.setAnswer("asdsda");
        answerEntity.setTaskId(1L);
        answerEntity.setUserId(1L);
        answerEntity.setCorrect(Boolean.FALSE);

        when(answerRepository.findByUserIdAndTaskId(1L, 1L)).thenReturn(Collections.singletonList(answerEntity));

        List<AnswerEntity> byTask = resultService.getByTask(1L);
        assertThat(byTask.isEmpty(), is(false));
    }
}