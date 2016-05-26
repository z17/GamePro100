package com.hackday.services;

import com.hackday.cmd.ExecTask;
import com.hackday.dao.AnswersDao;
import com.hackday.dao.TasksDao;
import com.hackday.entity.AnswerEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.entity.UserEntity;
import com.hackday.manager.TaskManager;
import com.hackday.results.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswersService {
    @Autowired
    private AnswersDao dao;

    @Autowired
    private TasksDao tasksDao;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TaskManager taskLoaderManager;

    public TaskResult submit(final String code, final Long taskID) {
        final TaskEntity taskEntity = tasksDao.get(taskID);
        final UserEntity userEntity = usersService.getCurrentUser();

        // todo: check access user to current lesson and task
        // todo: add all logic with task to own class

        final String taskPath = taskLoaderManager.getTaskPath(code, taskEntity);
        final TaskResult result = ExecTask.execTask(taskPath);
        taskLoaderManager.deleteTaskPath(taskPath);

        final AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswer(code);
        answerEntity.setCorrect(result.status == TaskResult.Status.COMPLETED);
        answerEntity.setTaskEntity(taskEntity);

        answerEntity.setUserEntity(userEntity);

        dao.create(answerEntity);
        return result;
    }

    public List<AnswerEntity> getByUserAndTask(final Long taskID) {
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskID);
        return dao.getByUserAndTask(usersService.getCurrentUser(), taskEntity);
    }
}
