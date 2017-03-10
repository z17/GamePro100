package service_client.result;

import lombok.NoArgsConstructor;
import service_client.data.TaskResult;

@NoArgsConstructor
public class TaskResultResponse extends Result<TaskResult> {
    public TaskResultResponse(String message, TaskResult data) {
        super(message, data);
    }
}
