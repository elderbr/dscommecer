package com.devsuperior.dscommece.controllers.handlers;

import com.devsuperior.dscommece.dto.CustomError;
import com.devsuperior.dscommece.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException erro, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(), status.value(), erro.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomError> dataIntegrityViolation(DataIntegrityViolationException error, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError customError = new CustomError(Instant.now(), status.value(), error.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }
}
