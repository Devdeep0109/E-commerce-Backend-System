package com.example.E_commerce.dto.userDto;

import com.example.E_commerce.customAnnotation.ValidName;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank
    @ValidName
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
