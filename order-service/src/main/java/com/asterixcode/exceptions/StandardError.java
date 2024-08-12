package com.asterixcode.exceptions;

import java.time.LocalDateTime;

public class StandardError {
  private final LocalDateTime timestamp;
  private final Integer status;
  private final String error;
  private final String message;
  private final String path;

  public StandardError(Integer status, String error, String message, String path) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public Integer getStatus() {
    return status;
  }

  public String getError() {
    return error;
  }

  public String getMessage() {
    return message;
  }

  public String getPath() {
    return path;
  }
}
