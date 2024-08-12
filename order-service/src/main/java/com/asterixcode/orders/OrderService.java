package com.asterixcode.orders;

import com.asterixcode.outbox.Outbox;
import com.asterixcode.exceptions.OutboxException;
import com.asterixcode.outbox.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final OutboxRepository outboxRepository;

  OrderService(OrderRepository orderRepository, OutboxRepository outboxRepository) {
    this.orderRepository = orderRepository;
    this.outboxRepository = outboxRepository;
  }

  @Transactional
  public Order create(CreateOrderRequest request) {
    Order order = new Order(request);
    order = orderRepository.save(order);
    try {
      Outbox outbox = new Outbox(order);
      outboxRepository.save(outbox);
    } catch (JsonProcessingException e) {
      throw new OutboxException("Error saving order in the outbox", e);
    }
    return order;
  }
}
