package executor.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import service_client.data.request.SubmitRequest;
import service_client.data.TaskResult;
import executor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service_client.result.Result;

import java.util.List;

import static service_client.result.Result.run;

@RestController
@RequestMapping
public class TaskController extends RestControllerAdvice {

    @Autowired
    private TaskService taskService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result<TaskResult> submit(@RequestBody final SubmitRequest request) {
        return run(() -> taskService.submit(request));
    }

    @RequestMapping(value = "/getMap/{taskId}", method = RequestMethod.GET)
    @Deprecated
    public Result<List<String>> getMap(@PathVariable final Long taskId) {
        return run(() -> taskService.getMap(taskId));
    }
}