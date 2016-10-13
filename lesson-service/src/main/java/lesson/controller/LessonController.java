package lesson.controller;


import lesson.entity.LessonEntity;
import lesson.request.LessonCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lesson.service.LessonsService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/lesson")
public class LessonController extends AbstractController{

    @Autowired
    LessonsService lessonService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<LessonEntity> get(@PathVariable final Long id) {
        return run(() -> lessonService.get(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result<List<LessonEntity>> getList() {
        return run(() -> lessonService.getList());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<LessonEntity> add(@RequestBody @Valid final LessonCreation lesson) {
        return run(() -> lessonService.add(lesson));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result<LessonEntity> update(@RequestBody final LessonEntity lesson) {
        return run(() -> lessonService.update(lesson));
    }
}
