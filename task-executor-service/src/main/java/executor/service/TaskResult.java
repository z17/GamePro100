package executor.service;

import lombok.Data;

@Data
public class TaskResult {

    public static TaskResult SuccessResult(final String text) {
        return new TaskResult(Status.COMPLETED, text);
    }

    public static TaskResult ErrorResult(final String text) {
        return new TaskResult(Status.ERROR, text);
    }

    private final Status status;
    private final String text;

    public enum Status {
        COMPLETED,
        ERROR
    }
}
