package com.hackday.controller;


import com.hackday.constants.Controllers;
import com.hackday.entity.LessonEntity;
import com.hackday.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Controllers.BASE_PATH + Controllers.LESSON)
public class LessonController {

    @Autowired
    LessonService lessonService;

    @RequestMapping(value = Controllers.GET, method = RequestMethod.GET)
    public LessonEntity get(@RequestParam(value = Controllers.PARAM_ID) final Long id) throws IOException {
        return new LessonEntity();
    }

    @RequestMapping(value = Controllers.GET_LIST, method = RequestMethod.GET)
    public List<LessonEntity> getList() throws IOException {
        return new ArrayList<>();
    }

    @RequestMapping(value = Controllers.CREATE, method = RequestMethod.GET)
    public boolean create(@RequestParam(value = Controllers.PARAM_NAME) final String name) throws IOException {
        LessonEntity lesson1 = new LessonEntity();
        lesson1.setName(name);
        lessonService.saveLesson(lesson1);
        return true;
    }
}
