package com.asterixcode.order_service.exceptions;

import java.util.List;

public class ValidationError extends StandardError {

  private final List<FieldError> errors;

  public ValidationError(
      Integer status, String error, String message, String path, List<FieldError> errors) {
    super(status, error, message, path);
    this.errors = errors;
  }

  public void addError(final String fieldName, final String message) {
    this.errors.add(new FieldError(fieldName, message));
  }

  public record FieldError(String fieldName, String message) {}
}
