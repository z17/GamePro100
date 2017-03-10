package executor.service;

import executor.executor.ExecutorResult;
import executor.executor.TaskExecutor;
import service_client.data.request.SubmitRequest;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import service_client.data.TaskResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class TaskService {
    // todo: transfer it to abother place
    public static final String CODE_REPLACE = "[CODE]";
    public static final String LESSONS_FOLDER = "lessons";
    public static final String TASK_FOLDER = "task";
    public static final String ANSWERS_FOLDER = "answers";
    public static final String MAIN_TASK_FILE = "Main";
    public static final String JAVA_EXTENSION = ".java";
    public static final String ERROR_TASK_RESULT = "FAIL";

    public TaskResult submit(final SubmitRequest request) {
        val path = prepareTask(request.getTaskId(), request.getCode());
        val taskExecutor = new TaskExecutor(path);
        val resultExec = taskExecutor.execTask();
        removeTaskFolder(path);
        return parseResult(resultExec);
    }


    Path prepareTask(final long taskId, final String code) {
        val tempTaskFolder = copyTaskFolder(taskId);
        insertUserCodeToTask(tempTaskFolder, code);
        return tempTaskFolder;
    }

    private Path copyTaskFolder(final long taskId) {
        // todo: user id of user, refactor this
        val taskFolder = Paths.get(LESSONS_FOLDER).resolve(TASK_FOLDER + taskId);
        val tempTaskFolder = Paths.get(ANSWERS_FOLDER).resolve(TASK_FOLDER + taskId);
        try {
            FileUtils.copyDirectory(taskFolder.toFile(), tempTaskFolder.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Task Error");
        }
        return tempTaskFolder;
    }

    private void insertUserCodeToTask(final Path taskFolder, final String code) {
        val mainFile = taskFolder.resolve(MAIN_TASK_FILE + JAVA_EXTENSION);
        try {
            String content = new String(Files.readAllBytes(mainFile));
            Files.write(mainFile, content.replace(CODE_REPLACE, code).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeTaskFolder(final Path path) {
        try {
            FileUtils.deleteDirectory(path.toFile());
        } catch (IOException e) {
            // todo: log this
            System.out.println("Error delete temp task folder");
        }
    }

    private TaskResult parseResult(final ExecutorResult resultExec) {
        if (resultExec.getErr().length() > 0)
            return TaskResult.CompileErrorResult(resultExec.getErr());

        if (resultExec.getOut().contains(ERROR_TASK_RESULT))
            return TaskResult.FailResult(resultExec.getOut());

        return TaskResult.SuccessResult(resultExec.getOut());
    }

    @Deprecated
    public List<String> getMap(final Long taskID) {
        try {
            return Files.readAllLines(Paths.get("lessons\\task1\\map"));
        } catch (IOException e) {
            throw new RuntimeException("map not found");
        }
    }
}
