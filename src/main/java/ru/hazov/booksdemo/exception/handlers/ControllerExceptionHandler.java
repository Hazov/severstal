package ru.hazov.booksdemo.exception.handlers;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.hazov.booksdemo.dto.error.ErrorResponse;
import ru.hazov.booksdemo.exception.ControllerException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(ControllerException ex) {
        return ResponseEntity.internalServerError().body(new ErrorResponse(ex.getMessage()));
    }
}