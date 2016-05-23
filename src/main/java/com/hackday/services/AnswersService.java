package com.hackday.services;

import com.hackday.cmd.ExecTask;
import com.hackday.dao.AnswersDao;
import com.hackday.entity.AnswerEntity;
import com.hackday.entity.TaskEntity;
import com.hackday.manager.TaskLoaderManager;
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
    private UsersService usersService;

    @Autowired
    private TaskLoaderManager taskLouderManager;

    public TaskResult submit(final String code, final Long taskID) {
        final String fileName = "Main";
        final String resultStr = taskLouderManager.getPathToTaskFolder(code, fileName, taskID);
        final TaskResult result = ExecTask.execTask(resultStr, fileName);

        final AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAnswer(code);
        answerEntity.setCorrect(result.status == TaskResult.Status.COMPLETED);

        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskID);
        answerEntity.setTaskEntity(taskEntity);

        answerEntity.setUserEntity(usersService.getCurrentUser());

        dao.create(answerEntity);
        return result;
    }

    public List<AnswerEntity> getByUserAndTask(final Long taskID) {
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskID);
        return dao.getByUserAndTask(usersService.getCurrentUser(), taskEntity);
    }
}
