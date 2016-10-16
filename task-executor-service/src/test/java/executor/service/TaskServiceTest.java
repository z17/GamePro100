package executor.service;

import executor.AbstractTest;
import service_client.data.request.SubmitRequest;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import service_client.data.TaskResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TaskServiceTest extends AbstractTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void submitTest() {
        val submit = new SubmitRequest(1L, "man.moveUp();");

        final TaskResult result = taskService.submit(submit);
        assertThat(result.getStatus(), is(TaskResult.Status.FAIL));
    }

    @Test
    public void syntaxErrorTest() {
        val submit = new SubmitRequest(1L, "man.moveUp()");
        val result = taskService.submit(submit);
        assertThat(result.getStatus(), is(TaskResult.Status.COMPILE_ERROR));
        assertThat(result.getText(), containsString("error: ';' expected"));
    }

    @Test
    public void tempTaskFolderRemoved() {
        val taskId = 1L;
        val submit = new SubmitRequest(taskId, "man.moveUp();");
        taskService.submit(submit);
        val tempTaskFolder = Paths.get("answer").resolve("task"+taskId);
        assertFalse(Files.exists(tempTaskFolder));
    }

    @Test
    public void submitTestTrueAnswer() {
        val code = "man.moveUp();" +
                "man.moveUp();" +
                "man.moveUp();" +
                "man.moveRight();" +
                "man.moveRight();";
        val submit = new SubmitRequest(1L, code);
        final TaskResult result = taskService.submit(submit);
        assertThat(result.getStatus(), is(TaskResult.Status.SUCCESS));
    }

    @Test
    public void prepareTaskTest() throws IOException {
        val taskId = 1L;
        val code = "man.moveDown();";
        final Path taskFolder = taskService.prepareTask(taskId, code);
        assertTrue(Files.exists(taskFolder));
        val mainFile = taskFolder.resolve("Main.java");
        assertTrue(Files.exists(mainFile));
        val content = new String(Files.readAllBytes(mainFile));
        assertThat(content, containsString(code));
    }

    @Test
    public void getMap() throws IOException {
        val map = taskService.getMap(1L);
        System.out.println(map);
        assertThat(map.isEmpty(), is(false));
    }
}