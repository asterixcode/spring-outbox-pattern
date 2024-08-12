package com.asterixcode.orders;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("orders")
public record Order(
    @Id Integer id,
    String name,
    @Column("customer_id") Integer customerId,
    @Column("product_type") String productType,
    int quantity,
    BigDecimal price,
    @Column("created_at") Date orderDate) {

  // constructor that maps from OrderRequest to Order
  public Order(CreateOrderRequest request) {
    this(
        null,
        request.name(),
        request.customerId(),
        request.productType(),
        request.quantity(),
        request.price(),
        new Date());
  }
}
