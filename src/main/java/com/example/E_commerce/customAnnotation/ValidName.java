package com.example.E_commerce.customAnnotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.FIELD })   //This annotation can be used on fields only.
@Retention(RetentionPolicy.RUNTIME)  //Keep this annotation available while the program is running.
public @interface ValidName {

    String message() default "Name must contains only alphabets!";

    Class<?>[] groups() default {};  //When should this validation run

    Class<? extends Payload>[] payload() default {}; //What extra info should I attach to this validation error

}
