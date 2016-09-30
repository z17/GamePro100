package com.hackday.repository;

import com.hackday.entity.LessonEntity;
import com.hackday.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {
    List<TaskEntity> getListByLesson(LessonEntity lesson);
}
