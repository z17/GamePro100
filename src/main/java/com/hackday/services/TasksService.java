package com.hackday.services;

import com.hackday.constants.Constants;
import com.hackday.dao.TasksDao;
import com.hackday.entity.LessonEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.manager.TaskManager;
import com.hackday.requests.TaskArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TasksService {

    @Autowired
    private TaskManager taskLouderManager;

    @Autowired
    private TasksDao taskDao;

    public TaskEntity get(final Long id) {
        return taskDao.get(id);
    }

    public List<TaskEntity> getListByLesson(final Long lessonID) {
        return taskDao.getListByLesson(lessonID);
    }

    public boolean create(final TaskArguments taskArgs) {
        TaskEntity task = new TaskEntity();
        task.setName(taskArgs.name);
        LessonEntity lessonTask = new LessonEntity();
        lessonTask.setId(taskArgs.lessonID);
        task.setLessonEntity(lessonTask);
        taskDao.create(task);
        return true;
    }

    public List<String> getMap(final int taskID) {
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
