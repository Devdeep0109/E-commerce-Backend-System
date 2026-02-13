package com.example.E_commerce.dto.orderDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {


    private Long orderId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;       // INR
    private BigDecimal convertedAmount;
    private String currency;
    private String status;

    private List<OrderItemResponse> items;

    @Data
    public static class OrderItemResponse {
        private Long productId;
        private String productName;
        private Long quantity;
        private BigDecimal priceAtTime;
    }
}
