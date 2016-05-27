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
    private TasksDao taskDao;

    public TaskEntity get(final Long id) {
        return taskDao.get(id);
    }

    public List<TaskEntity> getListByLesson(final Long lessonID) {
        return taskDao.getListByLesson(lessonID);
    }

    public boolean create(final TaskArguments taskArgs) {
        final TaskEntity task = new TaskEntity();
        task.setName(taskArgs.name);
        task.setDescription(taskArgs.description);
        final LessonEntity lessonTask = new LessonEntity();
        lessonTask.setId(taskArgs.lessonID);
        task.setLessonEntity(lessonTask);
        taskDao.create(task);
        return true;
    }

    @Deprecated
    public List<String> getMap(final Long taskID) {
        final String mapPath = taskDao.getMapPath(taskID);
        final List<String> map = new ArrayList<>();
        try {
            final BufferedReader br = new BufferedReader(new FileReader(Constants.MAP_TASK_1));

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                map.add(sCurrentLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("map not found");
        }
        return map;
    }
}
