package com.devcolibri.common.services;

import com.devcolibri.common.cmd.ExecTask;
import com.devcolibri.common.constants.Constants;
import com.devcolibri.common.manager.TaskLouderManager;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class TaskService {

    @Autowired
    TaskLouderManager taskLouderManager;

    public final String submit(final String code, final int taskID) throws IOException {
        String fileName = "Main";
        String result = taskLouderManager.getPathToTaskFolder(code, fileName, taskID);
        return ExecTask.execTask(result, fileName);
    }

    public final String getMap(final int taskID) {
        StringBuilder map = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Constants.MAP_TASK_1));

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                map.append(sCurrentLine);
                map.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map.toString();
    }
}
