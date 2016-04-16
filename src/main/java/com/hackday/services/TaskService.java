package com.hackday.services;

import com.hackday.cmd.ExecTask;
import com.hackday.constants.Constants;
import com.hackday.manager.TaskLouderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskLouderManager taskLouderManager;

    public final String submit(final String code, final int taskID) throws IOException {
        String fileName = "Main";
        String result = taskLouderManager.getPathToTaskFolder(code, fileName, taskID);
        return ExecTask.execTask(result, fileName);
    }

    public final List<String> getMap(final int taskID) {
        List<String> map = new ArrayList<>();
        //StringBuilder map = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Constants.MAP_TASK_1));

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                map.add(sCurrentLine);
//                map.append(sCurrentLine);
//                map.append(System.getProperty("line.separator"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
