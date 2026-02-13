package com.example.E_commerce.dto.cartDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartRequestDto {

    @NotNull
    private Long productId;

    @NotNull
    private Long userId;

    @NotNull
    @Min(value = 1)
    private Long quantity;
}
