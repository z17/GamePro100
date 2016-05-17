package com.hackday.controller;


import com.hackday.constants.Controllers;
import com.hackday.entity.LessonEntity;
import com.hackday.services.LessonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.LESSONS)
public class LessonsController extends AbstractController{

    @Autowired
    LessonsService lessonService;

    @RequestMapping(value = Controllers.GET, method = RequestMethod.GET)
    public Result<LessonEntity> get(@RequestParam(value = Controllers.PARAM_ID) final Long id) {
        return run(() -> lessonService.get(id));
    }

    @RequestMapping(value = Controllers.GET_LIST, method = RequestMethod.GET)
    public Result<List<LessonEntity>> getList() {
        return run(() -> lessonService.getList());
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = Controllers.CREATE, method = RequestMethod.POST)
    public Result<Boolean> create(@RequestBody final LessonEntity lessonEntity) {
        return run(() -> lessonService.create(lessonEntity));
    }
}
