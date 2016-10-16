package service_client.client;

import service_client.data.Task;
import service_client.data.request.TaskCreation;
import service_client.result.TaskListResult;
import service_client.result.TaskResult;

import java.util.List;

public class TaskClient extends Client {
    private static final String SERVICE_PATH = "/lesson-service/task/";

    public TaskClient() {
        super(SERVICE_PATH);
    }

    public Task get(final Long id) {
        return get(id.toString(), TaskResult.class).getData();
    }

    public List<Task> getList() {
        return get("", TaskListResult.class).getData();
    }

    public List<Task> getListByLesson(final Long lessonId) {
        return get("/getByLesson/" + lessonId, TaskListResult.class).getData();
    }

    public Task add(final TaskCreation taskCreation) {
        return post( "/add", taskCreation, TaskResult.class).getData();
    }
}
