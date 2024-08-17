package com.asterixcode.order_service.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(OutboxException.class)
  ResponseEntity<StandardError> handleResourceNotFoundException(
      final OutboxException ex, final HttpServletRequest request) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(
            new StandardError(
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<ValidationError> handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException ex, final HttpServletRequest request) {
    var errors =
        new ValidationError(
            BAD_REQUEST.value(),
            "Validation Error",
            "Exception in validation fields",
            request.getRequestURI(),
            new ArrayList<>());

    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.addError(fieldError.getField(), fieldError.getDefaultMessage());
    }

    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(Exception.class)
  ResponseEntity<StandardError> handleException(
      final Exception ex, final HttpServletRequest request) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(
            new StandardError(
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()));
  }
}
