package service_client.client;

import service_client.data.request.SubmitRequest;
import service_client.data.TaskResult;
import service_client.result.TaskResultResponse;

public class ExecutorClient extends Client {
    private static final String SERVICE_PATH = "/task-executor-service";

    public ExecutorClient() {
        super(SERVICE_PATH);
    }

    public TaskResult submit(final SubmitRequest request, final String token) {
        return post("/submit", request, TaskResultResponse.class, token).getData();
    }
}
