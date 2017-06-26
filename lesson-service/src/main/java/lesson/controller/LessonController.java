package lesson.controller;


import lesson.entity.LessonEntity;
import lesson.request.LessonCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import lesson.service.LessonsService;
import service_client.data.Lesson;
import service_client.result.Result;

import javax.validation.Valid;
import java.util.List;

import static service_client.result.Result.run;


@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonsService lessonService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Lesson> get(@PathVariable final Long id) {
        return run(() -> lessonService.get(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<List<Lesson>> getList() {
        return run(() -> lessonService.getList());
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<Lesson> add(@RequestBody @Valid final LessonCreation lesson) {
        return run(() -> lessonService.add(lesson));
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<Lesson> update(@RequestBody final LessonEntity lesson) {
        return run(() -> lessonService.update(lesson));
    }
}
