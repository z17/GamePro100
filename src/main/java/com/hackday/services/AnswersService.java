package com.hackday.services;

import com.hackday.entity.AnswerEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.entity.UserEntity;
import com.hackday.manager.TaskManager;
import com.hackday.repository.AnswerRepository;
import com.hackday.repository.TaskRepository;
import com.hackday.results.TaskResult;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswersService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TaskManager taskManager;

    public TaskResult submit(final String code, final Long taskID) {
        val taskEntity = taskRepository.findOne(taskID);
        if (taskEntity == null)
            throw new RuntimeException("Invalid task id");

        val userEntity = usersService.getCurrentUser();
        // todo: check access user to current lesson and task

        val answerEntity = new AnswerEntity();
        answerEntity.setAnswer(code);
        answerEntity.setTask(taskEntity);
        answerEntity.setUser(userEntity);
        val result = taskManager.run(answerEntity);
        answerEntity.setCorrect(result.status == TaskResult.Status.COMPLETED);

        answerRepository.save(answerEntity);
        return result;
    }

    public List<AnswerEntity> getByUserAndTask(final Long taskID) {
        val taskEntity = new TaskEntity();
        taskEntity.setId(taskID);
        return answerRepository.findByUserAndTask(usersService.getCurrentUser(), taskEntity);
    }
}
