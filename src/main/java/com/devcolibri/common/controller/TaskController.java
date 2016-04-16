package com.devcolibri.common.controller;

import com.devcolibri.common.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/services/task")
public class TaskController {
    @Autowired
    private TaskService service;

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String submit(@RequestParam(value = "code") final String code) {
        return service.test();
    }
}
