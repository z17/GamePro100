package com.hackday.manager;

import com.hackday.constants.Constants;
import com.hackday.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TaskLoaderManager {


    public String getTaskPath(final String userCode, final String fileName, final TaskEntity task) {

        final String folderPath = makeFolders(task);
        final File mainFile = makeFile(folderPath);

        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(mainFile))) {
                writer.write(Constants.FILE_HEADER);
                writer.write(userCode);
                writer.write(Constants.FILE_FLOR);
                return folderPath;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error with create code file");
        }
    }

    public Boolean deleteTaskPath(final String path) {
        return null;
    }


    private String makeFolders(final TaskEntity taskEntity) {
        final String path = Constants.TASKS_FOLDER + "\\lesson" + taskEntity.getLessonEntity().getId() + "\\task" + taskEntity.getId();
        if (new File(path).mkdirs()) {
            return path;
        } else {
            throw new RuntimeException("Error creating files");
        }
    }

    private File makeFile(final String folder) {
        return new File(folder, "Main.java");
    }

}
