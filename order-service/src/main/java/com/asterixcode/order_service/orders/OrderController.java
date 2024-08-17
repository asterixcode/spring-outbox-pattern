package com.asterixcode.order_service.orders;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController implements OrderControllerOpenApi {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @Override
  public Order createOrder(CreateOrderRequest request) {
    return orderService.create(request);
  }
}
