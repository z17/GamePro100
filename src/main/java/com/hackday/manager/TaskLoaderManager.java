package com.hackday.manager;

import com.hackday.constants.Constants;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TaskLoaderManager {

    public String getPathToTaskFolder(final String userCode, final String fileName, final Long taskID) {
        try {
            File file = createTmpFile(fileName, taskID);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(Constants.FILE_HEADER);
                writer.write(userCode);
                writer.write(Constants.FILE_FLOR);
                return (file.getParent());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error with create code file");
        }
    }


    private File createTmpFile(final String fileName, final Long taskID) throws IOException {
        return new File(Constants.TASKS_FOLDER + "/" + taskID, fileName + ".java");
    }
}
