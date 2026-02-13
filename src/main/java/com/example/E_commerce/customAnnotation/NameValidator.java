package com.example.E_commerce.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // Regex: only letters and spaces
        return value.matches("^[A-Za-z ]+$");
    }
}
