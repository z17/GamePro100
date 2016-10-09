package executor.service;

import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TaskService {
    public TaskResult submit(final long taskId, final String code) {
        val path = prepareTask(taskId, code);
        val taskExecutor = new TaskExecutor(path);
        val result = taskExecutor.execTask();
        removeTaskFolder(path);
        return result;
    }

    Path prepareTask(final long taskId, final String code) {
        val tempTaskFolder = copyTaskFolder(taskId);
        insertUserCodeToTask(tempTaskFolder, code);
        return tempTaskFolder;
    }

    private Path copyTaskFolder(final long taskId) {
        // todo: dont hardcode folders, user id of user, refactor this
        val taskFolder = Paths.get("lessons").resolve("lesson1").resolve("task" + taskId);
        val tempTaskFolder = Paths.get("answer").resolve("task"+taskId);
        try {
            FileUtils.copyDirectory(taskFolder.toFile(), tempTaskFolder.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Task Error");
        }
        return tempTaskFolder;
    }

    private void insertUserCodeToTask(final Path taskFolder, final String code) {
        val mainFile = taskFolder.resolve("Main.java");
        try {
            String content = new String(Files.readAllBytes(mainFile));
            Files.write(mainFile, content.replaceAll("\\[CODE\\]", code).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeTaskFolder(final Path path) {
        try {
            FileUtils.deleteDirectory(path.toFile());
        } catch (IOException e) {
            throw new RuntimeException("Error delete temp task folder", e);
        }
    }
}
