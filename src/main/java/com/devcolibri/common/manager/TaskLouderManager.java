package com.devcolibri.common.manager;

import com.devcolibri.common.constanse.Constanse;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TaskLouderManager {

    public String getPathToMainFile(final String userCode) throws IOException {
        File file = createTmpFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(Constanse.FILE_HEADER);
            writer.write(userCode);
            writer.write(Constanse.FILE_FLOR);
        }
        return (file.getAbsolutePath());
    }


    private File createTmpFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis()).append("tmp-name");
        File temp = File.createTempFile(stringBuilder.toString(), ".java");
        return temp;
    }

}
