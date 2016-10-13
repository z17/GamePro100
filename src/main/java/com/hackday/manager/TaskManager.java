package com.hackday.manager;

import com.hackday.cmd.ExecTask;
import com.hackday.constants.Constants;
import com.hackday.entity.AnswerEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.results.TaskResult;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class TaskManager {

    public TaskResult run(final AnswerEntity answerEntity) {
        val taskPath = getTaskPath(answerEntity.getAnswer(), answerEntity.getTask(), answerEntity.getUser().getId());
        val result = ExecTask.execTask(taskPath);
        deleteTaskPath(taskPath);
        return result;
    }

    private String getTaskPath(final String userCode, final TaskEntity task, final Long userID) {

        val folderPath = makeFolders(task, userID);
        loadTask(folderPath, task, userCode);
        return folderPath;
    }

    private void deleteTaskPath(final String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("load task error");
        }
    }

    private void loadTask(final String path, final TaskEntity taskEntity, final String code) {
        val taskFolder = "lessons\\Lesson" + taskEntity.getLesson().getId() + "\\src\\task" + taskEntity.getId();
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


    private String makeFolders(final TaskEntity taskEntity, final Long userID) {
        val path = Constants.TASKS_FOLDER + "\\lesson" + taskEntity.getLesson().getId() + "\\task" + taskEntity.getId() + "\\user" + userID + "\\";
        if (new File(path).mkdirs())
            return path;

        throw new RuntimeException("Error creating files");
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
}
