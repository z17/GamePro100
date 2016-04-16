package com.devcolibri.common.manager;

import com.devcolibri.common.constants.Constants;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TaskLouderManager {

    public String getPathToMainFile(final String userCode, final String fileName) throws IOException {
        File file = createTmpFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(Constants.FILE_HEADER);
            writer.write(userCode);
            writer.write(Constants.FILE_FLOR);
        }
        return (file.getParent());

    }


    private File createTmpFile(final String fileName) throws IOException {
        return new File(Constants.TASKS_FOLDER, fileName + ".java");
    }

}
