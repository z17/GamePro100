package com.hackday.repository;

import com.hackday.entity.AnswerEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByUserAndTask(UserEntity currentUser, TaskEntity taskEntity);
}
