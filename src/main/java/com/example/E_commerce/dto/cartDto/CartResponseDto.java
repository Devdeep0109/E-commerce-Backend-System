package com.example.E_commerce.dto.cartDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartResponseDto {

    private Long cartId;
    private Long userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<CartItemResponseDto> items;

    private Long totalItems;
    private BigDecimal totalAmount;
}
