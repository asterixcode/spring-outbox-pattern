package com.asterixcode.outbox;

import com.asterixcode.orders.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("outbox")
public record Outbox(
    @Id Integer id,
    @Column("aggregate_id") String aggregateId,
    String payload,
    @Column("created_at") Date createdAt,
    boolean processed) {

  // constructor that maps from Order to Outbox
  public Outbox(Order order) throws JsonProcessingException {
    this(
        null,
        order.id().toString(),
        new ObjectMapper().writeValueAsString(order),
        new Date(),
        false);
  }
}
