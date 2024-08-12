package com.asterixcode.exceptions;

import java.util.List;

public class ValidationError extends StandardError {

  private final List<FieldError> errors;

  public ValidationError(
      Integer status, String error, String message, String path, List<FieldError> errors) {
    super(status, error, message, path);
    this.errors = errors;
  }

  private record FieldError(String fieldName, String message) {}

  public void addError(final String fieldName, final String message) {
    this.errors.add(new FieldError(fieldName, message));
  }
}
