package com.bahubba.bahubbabookclubreactive.model.document.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PublicityValidator implements ConstraintValidator<PublicityConstraint, String> {
    private static final String PUBLIC = "PUBLIC";
    private static final String OBSERVABLE = "OBSERVABLE";
    private static final String PRIVATE = "PRIVATE";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equals(PUBLIC) || value.equals(OBSERVABLE) || value.equals(PRIVATE));
    }
}
