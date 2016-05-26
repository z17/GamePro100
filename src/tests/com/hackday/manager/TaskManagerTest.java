package com.hackday.manager;

import com.hackday.entity.LessonEntity;
import com.hackday.entity.TaskEntity;
import org.junit.Test;

public class TaskManagerTest {

    @Test
    public void testGetPathToTaskFolder() throws Exception {
        final TaskEntity task = new TaskEntity();
        task.setId(1L);
        final LessonEntity lessonEntity = new LessonEntity();
        lessonEntity.setId(1L);
        task.setLessonEntity(lessonEntity);
        TaskManager taskLoaderManager = new TaskManager();
        final String path = taskLoaderManager.getTaskPath("", task);
    }
}