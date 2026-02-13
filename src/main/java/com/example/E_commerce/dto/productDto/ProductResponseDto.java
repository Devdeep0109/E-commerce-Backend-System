package com.example.E_commerce.dto.productDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stockQuantity;
    private LocalDateTime createdAt;
    private Long categoryId;
    private String categoryName;
}
