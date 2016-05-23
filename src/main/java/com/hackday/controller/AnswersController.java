package com.hackday.controller;

import com.hackday.constants.Controllers;
import com.hackday.entity.AnswerEntity;
import com.hackday.results.TaskResult;
import com.hackday.services.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.ANSWERS)
public class AnswersController extends AbstractController {

    @Autowired
    private AnswersService service;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Controllers.TASK_SUBMIT, method = RequestMethod.GET)
    public Result<TaskResult> submit(@RequestParam(value = Controllers.PARAM_CODE) final String code,
                                     @RequestParam(value = Controllers.PARAM_ID) final Long taskID ){
        return run(() -> service.submit(code, taskID));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = Controllers.GET_BY_USER_AND_TASK, method = RequestMethod.GET)
    public Result<List<AnswerEntity>> getByUserAndTask(@RequestParam(value = Controllers.PARAM_TASK_ID) final Long taskID) {
        return run(() -> service.getByUserAndTask(taskID));
    }
}