package com.hackday.manager;

import com.hackday.constants.Constants;
import com.hackday.entity.TaskEntity;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class TaskManager {

    public String getTaskPath(final String userCode, final TaskEntity task) {

        final String folderPath = makeFolders(task);
        loadTask(folderPath, task, userCode);
        return folderPath;
    }

    public void deleteTaskPath(final String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("load task error");
        }
    }

    private void loadTask(final String path, final TaskEntity taskEntity, final String code) {
        final String taskFolder = Constants.PROJECT_ROOT + "\\lessons\\Lesson" + taskEntity.getLessonEntity().getId() + "\\src\\task" + taskEntity.getId();
        try {
            Files.walk(Paths.get(taskFolder)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    if (filePath.getFileName().toString().equals("Main.java")) {
                        createCodeFile(filePath, path, code);
                    } else {
                        copyFile(filePath, path);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("load task error");
        }
    }


    private String makeFolders(final TaskEntity taskEntity) {
        final String path = Constants.TASKS_FOLDER + "\\lesson" + taskEntity.getLessonEntity().getId() + "\\task" + taskEntity.getId() + "\\";
        if (new File(path).mkdirs()) {
            return path;
        } else {
            throw new RuntimeException("Error creating files");
        }
    }

    private void copyFile(final Path filePath, final String folder) {
        try {
            FileUtils.copyFile(filePath.toFile(), new File(folder + filePath.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException("Error creating files");
        }
    }

    private void createCodeFile(final Path filePath, final String folder, final String code) {
        try {
            final String textFile = new String(Files.readAllBytes(filePath));
            final String outTextFile = textFile.replace("[CODE]", code);
            try (final PrintWriter out = new PrintWriter(folder + filePath.getFileName())) {
                out.print(outTextFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating files");
        }
    }

    private File makeFile(final String folder) {
        return new File(folder, "Main.java");
    }

}
