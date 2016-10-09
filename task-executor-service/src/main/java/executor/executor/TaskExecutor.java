package executor.executor;


import executor.service.TaskService;
import lombok.val;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class TaskExecutor {
    final Path taskFolder;

    public TaskExecutor(final Path taskFolder) {
        this.taskFolder = taskFolder;
    }

    public ExecutorResult execTask() {
        val absolutePath = taskFolder.toAbsolutePath();
        val commands = Arrays.asList(
                "cd " + absolutePath,
                "javac " + TaskService.MAIN_TASK_FILE + TaskService.JAVA_EXTENSION,
                "java " + TaskService.MAIN_TASK_FILE
        );
        try {

            val process = Runtime.getRuntime().exec("cmd /c " + String.join(" & ", commands));
            process.waitFor();

            val out = getFromStream(process.getInputStream());
            val err = getErrorsFromStream(process.getErrorStream());
            return new ExecutorResult(out, err);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException("Error task execution", e);
        }
    }

    private String getFromStream(final InputStream inputStream) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {
            return input.lines().collect(Collectors.joining(""));
        } catch (IOException e) {
            throw new RuntimeException("Error read from stdout", e);
        }
    }

    private String getErrorsFromStream(final InputStream inputStream) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(inputStream))) {
            return input.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Error read from stdout", e);
        }
    }
}
