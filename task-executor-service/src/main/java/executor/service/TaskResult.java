package executor.service;

import lombok.Getter;

@Getter
public class TaskResult {
    private final Status status;
    private final String text;

    private TaskResult(final Status status, final String text) {
        this.status = status;
        this.text = text;
    }

    public static TaskResult SuccessResult(final String text) {
        return new TaskResult(Status.SUCCESS, text);
    }
    public static TaskResult FailResult(final String text) {
        return new TaskResult(Status.FAIL, text);
    }
    public static TaskResult CompileErrorResult(final String text) {
        return new TaskResult(Status.COMPILE_ERROR, text);
    }

    public enum Status {
        SUCCESS,
        FAIL,
        COMPILE_ERROR
    }
}
