package executor.service;

import executor.AbstractTest;
import lombok.val;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        val code = "man.moveUp();";
        val taskId = 1L;

        final TaskResult result = taskService.submit(taskId, code);
        assertThat(result.getStatus(), is(TaskResult.Status.FAIL));
    }

    @Test
    public void syntaxErrorTest() {
        val code = "man.moveUp()";
        val taskId = 1L;
        val result = taskService.submit(taskId, code);
        assertThat(result.getStatus(), is(TaskResult.Status.COMPILE_ERROR));
        assertThat(result.getText(), containsString("error: ';' expected"));
    }

    @Test
    public void tempTaskFolderRemoved() {
        val code = "man.moveUp();";
        val taskId = 1L;

        taskService.submit(taskId, code);
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
        val taskId = 1L;

        final TaskResult result = taskService.submit(taskId, code);
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
}