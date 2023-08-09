package com.bahubba.bahubbabookclubreactive.model.document.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<RoleConstraint, String> {
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equals(ADMIN) || value.equals(USER));
    }
}
