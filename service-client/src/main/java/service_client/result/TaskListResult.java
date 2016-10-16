package service_client.result;

import lombok.NoArgsConstructor;
import service_client.data.Task;

import java.util.List;

@NoArgsConstructor
public class TaskListResult extends Result<List<Task>> {
    public TaskListResult(String message, List<Task> data) {
        super(message, data);
    }
}
