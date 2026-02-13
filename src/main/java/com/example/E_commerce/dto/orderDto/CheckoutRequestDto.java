package com.example.E_commerce.dto.orderDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckoutRequestDto {

    @NotNull(message = "User ID is required")
    private Long userId;


    @NotBlank(message = "Currency is required")
    private String currency;
}
