package com.hackday.results;

public class TaskResult {

    public Status status;
    public String text;

    public enum Status {
        COMPLETED,
        ERROR
    }
}
