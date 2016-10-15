package executor.controller;

import service_client.data.request.SubmitRequest;
import service_client.data.TaskResult;
import executor.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TaskController extends AbstractController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Result<TaskResult> submit(@RequestBody final SubmitRequest request) {
        return run(() -> taskService.submit(request));
    }
}