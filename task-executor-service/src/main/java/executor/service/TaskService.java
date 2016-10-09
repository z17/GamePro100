package executor.service;

import lombok.val;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class TaskService {
    public TaskResult submit(final long taskId, final String code) {
        val result = new TaskResult(null, null);
        return result;
    }

    public Path prepareTask(final long taskId, final String code) {
        return null;
    }
}
