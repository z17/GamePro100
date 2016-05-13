package com.hackday.controller;

import org.apache.logging.log4j.util.Supplier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class AbstractController {

    protected final <T> Result<T> run(final Supplier<T> function ) {
        final T result = function.get();
        return Result.success(result);
    }

    private static String getReason(final Throwable exception) {
        Throwable reason = exception;
        while (reason.getCause() != null)
            reason = reason.getCause();
        return reason.getMessage();
    }

    public final static class Result<T> {
        public final String message;
        public final T data;

        public static <T> Result<T> success(final T data) {
            return new Result<>(null, data);
        }

        public static <T> Result<T> error(final String message) {
            return new Result<>(message, null);
        }

        private Result(final String msg, final T data) {
            this.message  = msg;
            this.data  = data;
        }
    }

    @ControllerAdvice
    static class RestControllerAdvice {
        private static final Integer DEFAULT_STATUS_CODE = 500;

        @ExceptionHandler(Exception.class)
        @ResponseBody
        final ResponseEntity<?> handle(final Exception exception) {
            return new ResponseEntity<>(Result.error(getReason(exception)), HttpStatus.valueOf(DEFAULT_STATUS_CODE));
        }
    }
}
