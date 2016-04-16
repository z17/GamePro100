package com.devcolibri.common.services;

import com.devcolibri.common.cmd.ExecTask;
import com.devcolibri.common.manager.TaskLouderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
