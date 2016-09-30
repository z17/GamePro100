package com.hackday.services;

import com.hackday.constants.Constants;
import com.hackday.entity.LessonEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.repository.LessonRepository;
import com.hackday.repository.TaskRepository;
import com.hackday.requests.TaskArguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TasksService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public TaskEntity get(final Long id) {
        return taskRepository.findOne(id);
    }

    public List<TaskEntity> getListByLesson(final Long lessonID) {
        return taskRepository.getListByLesson(lessonRepository.findOne(lessonID));
    }

    public boolean create(final TaskArguments taskArgs) {
        final TaskEntity task = new TaskEntity();
        task.setName(taskArgs.name);
        task.setDescription(taskArgs.description);
        final LessonEntity lessonTask = new LessonEntity();
        lessonTask.setId(taskArgs.lessonID);
        task.setLesson(lessonTask);
        taskRepository.save(task);
        return true;
    }

    @Deprecated
    public List<String> getMap(final Long taskID) {
//        final String mapPath = taskRepository.findOne(taskID).getMapPath();
        try {
            return Files.readAllLines(Paths.get(Constants.MAP_TASK_1));
        } catch (IOException e) {
            throw new RuntimeException("map not found");
        }
    }
}
