package com.example.E_commerce.dto.productDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStockRequestDto {

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0)
    private Long stockQuantity;
}
