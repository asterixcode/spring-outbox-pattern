package com.asterixcode.orders;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record CreateOrderRequest(
    @Schema(description = "Name", example = "Macbook Pro 14 inch M1")
        @NotBlank(message = "The name cannot be null or empty")
        String name,
    @Schema(description = "Customer ID", example = "1")
        @NotBlank(message = "The customerId cannot be null or empty")
        Integer customerId,
    @Schema(description = "Product type", example = "Computer")
        @NotBlank(message = "The productType cannot be null or empty")
        String productType,
    @Schema(description = "Quantity", example = "1")
        @Min(value = 1, message = "The quantity must be greater than 0")
        int quantity,
    @Schema(description = "Price", example = "1800.00")
        @DecimalMin(value = "0.0", inclusive = false, message = "The price must be greater than 0")
        BigDecimal price) {}
