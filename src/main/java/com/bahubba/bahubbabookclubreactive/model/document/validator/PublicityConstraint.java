package com.bahubba.bahubbabookclubreactive.model.document.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PublicityValidator.class)
public @interface PublicityConstraint {
    String message() default "Invalid publicity value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
