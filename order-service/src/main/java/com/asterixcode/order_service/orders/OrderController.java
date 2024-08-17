package com.asterixcode.order_service.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderControllerOpenApi {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public ResponseEntity<Order> createOrder(CreateOrderRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
  }
}
