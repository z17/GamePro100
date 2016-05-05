package com.hackday.controller;


import com.hackday.constants.Controllers;
import com.hackday.entity.LessonEntity;
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

    @RequestMapping(value = Controllers.GET, method = RequestMethod.GET)
    public LessonEntity get(@RequestParam(value = Controllers.PARAM_ID) final Long id) throws IOException {
        return new LessonEntity();
    }

    @RequestMapping(value = Controllers.GET_LIST, method = RequestMethod.GET)
    public List<LessonEntity> getList() throws IOException {
        return new ArrayList<>();
    }
}
