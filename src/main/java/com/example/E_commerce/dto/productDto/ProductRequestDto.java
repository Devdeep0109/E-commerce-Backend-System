package com.example.E_commerce.dto.productDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    @NotBlank
    private String name;
    private String description;

    @NotNull
    private BigDecimal price;
    @NotNull
    @Min(value = 0)
    private Long stockQuantity;
    @NotNull
    private Long categoryId;
}
