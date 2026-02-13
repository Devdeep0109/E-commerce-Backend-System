package com.example.E_commerce.dto.cartDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponseDto {

    private Long cartItemId;

    private Long productId;
    private String productName;

    private BigDecimal priceAtTime;
    private Long quantity;

    private BigDecimal itemTotal; // priceAtTime * quantity
}
