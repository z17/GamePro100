package lesson.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import service_client.result.Result;

import java.util.function.Supplier;

@ControllerAdvice
public final class RestControllerAdvice {
    private static final Integer DEFAULT_STATUS_CODE = 500;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    final ResponseEntity<?> handle(final Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(Result.error(getReason(exception)), HttpStatus.valueOf(DEFAULT_STATUS_CODE));
    }

    private static String getReason(final Throwable exception) {
        Throwable reason = exception;
        while (reason.getCause() != null)
            reason = reason.getCause();
        return reason.getMessage();
    }
}
