package lesson.controller;

import lesson.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import service_client.data.Task;
import service_client.data.request.TaskCreation;
import service_client.result.Result;

import javax.validation.Valid;
import java.util.List;

import static service_client.result.Result.run;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TasksService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Task> get(@PathVariable final Long id) {
        return run(() -> service.get(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<List<Task>> getList() {
        return run(() -> service.getList());
    }

    @RequestMapping(value = "/getByLesson/{id}", method = RequestMethod.GET)
    public Result<List<Task>> getListByLesson(@PathVariable final Long id) {
        return run(() -> service.getListByLesson(id));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Task> add(@RequestBody @Valid final TaskCreation taskArgs){
        return run(() -> service.add(taskArgs));
    }
}
