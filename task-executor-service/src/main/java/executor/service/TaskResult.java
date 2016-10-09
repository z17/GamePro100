package executor.service;

import lombok.Data;

@Data
public class TaskResult {

    private final Status status;
    private final String text;

    public enum Status {
        COMPLETED,
        ERROR
    }
}
