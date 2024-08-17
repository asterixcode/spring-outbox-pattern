package com.asterixcode.order_service.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

public class OutboxException extends RuntimeException {
  public OutboxException(String s, JsonProcessingException e) {
    super(s, e);
  }
}
