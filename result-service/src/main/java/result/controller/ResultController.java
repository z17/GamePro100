package result.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import result.entity.AnswerEntity;
import result.service.ResultService;
import service_client.data.TaskResult;
import service_client.data.request.SubmitRequest;
import service_client.result.Result;

import java.util.List;

import static service_client.result.Result.run;


@RestController
@RequestMapping
public class ResultController {

    @Autowired
    private ResultService service;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result<TaskResult> submit(@RequestBody final SubmitRequest submit, @RequestHeader("token") String token){
        return run(() -> service.submit(submit, token));
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/getByTask/{id}", method = RequestMethod.GET)
    public Result<List<AnswerEntity>> getByTask(@PathVariable final Long id) {
        return run(() -> service.getByTask(id));
    }
}