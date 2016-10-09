package executor.service;

import executor.AbstractTest;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TaskServiceTest extends AbstractTest {
    @Autowired
    private TaskService taskService;

    public void submitTest() {
        val code = "man.moveUp()";
        val taskId = 1L;

        final TaskResult result = taskService.submit(taskId, code);
        assertThat(result.getStatus(), is(TaskResult.Status.ERROR));
    }

    public void prepareTaskTest() {
        val taskId = 1L;
        val code = "some part of code";
        Path taskFolder = taskService.prepareTask(taskId, code);
        assertTrue(Files.exists(taskFolder));
    }
}