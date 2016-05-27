package com.hackday.controller;

import com.hackday.constants.Controllers;
import com.hackday.constants.Params;
import com.hackday.entity.TaskEntity;
import com.hackday.requests.TaskArguments;
import com.hackday.results.TaskResult;
import com.hackday.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.TASKS)
public class TasksController extends AbstractController {

    @Autowired
    private TasksService service;

    @RequestMapping(value = Controllers.GET, method = RequestMethod.GET)
    public Result<TaskEntity> get(@RequestParam(value = Params.ID) final Long id) {
        return run(() -> service.get(id));
    }

    @RequestMapping(value = Controllers.GET_LIST, method = RequestMethod.GET)
    public Result<List<TaskEntity>> getList(@RequestParam(value = Params.LESSON_ID) final Long lessonID) {
        return run(() -> service.getListByLesson(lessonID));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = Controllers.CREATE, method = RequestMethod.POST)
    public Result<Boolean> create(@RequestBody @Valid final TaskArguments taskArgs){
        return run(() -> service.create(taskArgs));
    }


    // todo: delete this method, path to map will be in TaskEntity,and return to frontend from get method
    @Deprecated
    @RequestMapping(value = Controllers.GET_MAP, method = RequestMethod.GET)
    public Result<List<String>> getMap(@RequestParam(value = Params.ID) final Long taskID) {
        return run(() -> service.getMap(taskID));
    }
}
