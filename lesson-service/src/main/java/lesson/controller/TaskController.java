package lesson.controller;

import lesson.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lesson.request.TaskArguments;
import lesson.service.TasksService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController extends AbstractController {

    @Autowired
    private TasksService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<TaskEntity> get(@PathVariable final Long id) {
        return run(() -> service.get(id));
    }

    @RequestMapping(value = "/getByLesson/{id}", method = RequestMethod.GET)
    public Result<List<TaskEntity>> getList(@PathVariable final Long id) {
        return run(() -> service.getListByLesson(id));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Boolean> add(@RequestBody @Valid final TaskArguments taskArgs){
        return run(() -> service.add(taskArgs));
    }
}
