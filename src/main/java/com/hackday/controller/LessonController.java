package com.hackday.controller;


import com.hackday.constants.Controllers;
import com.hackday.entity.LessonEntity;
import com.hackday.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.LESSON)
public class LessonController {

    @Autowired
    LessonService lessonService;

    @RequestMapping(value = Controllers.GET, method = RequestMethod.POST)
    public LessonEntity get(@RequestParam(value = Controllers.PARAM_ID) final Long id) {
        return lessonService.get(id);
    }

    @RequestMapping(value = Controllers.GET_LIST, method = RequestMethod.POST)
    public List<LessonEntity> getList() {
        return lessonService.getList();
    }

    @RequestMapping(value = Controllers.CREATE, method = RequestMethod.POST)
    public boolean create(@RequestBody final LessonEntity lessonEntity) {
        return lessonService.create(lessonEntity);
    }
}
