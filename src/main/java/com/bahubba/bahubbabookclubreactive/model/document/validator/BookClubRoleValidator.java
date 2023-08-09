package com.bahubba.bahubbabookclubreactive.model.document.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BookClubRoleValidator implements ConstraintValidator<BookClubRoleConstraint, String> {
    private static final String ADMIN = "ADMIN";
    private static final String READER = "READER";
    private static final String PARTICIPANT = "PARTICIPANT";
    private static final String OBSERVER = "OBSERVER";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (
            value.equals(ADMIN) || value.equals(READER) || value.equals(PARTICIPANT) || value.equals(OBSERVER)
        );
    }
}
