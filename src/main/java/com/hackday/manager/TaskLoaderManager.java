package com.hackday.manager;

import com.hackday.constants.Constants;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TaskLoaderManager {

    public String getPathToTaskFolder(final String userCode, final String fileName, final int taskID) throws IOException {
        File file = createTmpFile(fileName, taskID);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(Constants.FILE_HEADER);
            writer.write(userCode);
            writer.write(Constants.FILE_FLOR);
        }
        return (file.getParent());
    }


    private File createTmpFile(final String fileName, final int taskID) throws IOException {
        return new File(Constants.TASKS_FOLDER + "/" + taskID, fileName + ".java");
    }
}
