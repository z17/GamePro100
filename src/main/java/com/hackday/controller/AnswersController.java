package com.hackday.controller;

import com.hackday.constants.Controllers;
import com.hackday.results.TaskResult;
import com.hackday.services.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.ANSWERS)
public class AnswersController extends AbstractController {

    @Autowired
    private AnswersService service;

    @Secured("ROLE_USER")
    @RequestMapping(value = Controllers.TASK_SUBMIT, method = RequestMethod.GET)
    public Result<TaskResult> submit(@RequestParam(value = Controllers.PARAM_CODE) final String code,
                                     @RequestParam(value = Controllers.PARAM_ID) final Long taskID ){
        return run(() -> service.submit(code, taskID));
    }
}