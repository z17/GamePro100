package service_client.result;

import lombok.NoArgsConstructor;
import service_client.data.Task;

@NoArgsConstructor
public class TaskResult extends Result<Task> {
    public TaskResult(String message, Task data) {
        super(message, data);
    }
}