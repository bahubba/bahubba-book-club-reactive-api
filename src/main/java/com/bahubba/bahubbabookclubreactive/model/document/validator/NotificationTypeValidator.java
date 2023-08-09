package com.bahubba.bahubbabookclubreactive.model.document.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotificationTypeValidator implements ConstraintValidator<NotificationTypeConstraint, String> {
    private static final String REGISTERED = "REGISTERED";
    private static final String INVITED_TO_CLUB = "INVITED_TO_CLUB";
    private static final String MEMBERSHIP_REQUESTED = "MEMBERSHIP_REQUESTED";
    private static final String MEMBERSHIP_DECLINED = "MEMBERSHIP_DECLINED";
    private static final String MEMBERSHIP_APPROVED = "MEMBERSHIP_APPROVED";
    private static final String MEMBERSHIP_OFFERED = "MEMBERSHIP_OFFERED";
    private static final String MEMBERSHIP_REJECTED = "MEMBERSHIP_REJECTED";
    private static final String MEMBERSHIP_ACCEPTED = "MEMBERSHIP_ACCEPTED";
    private static final String NEW_READER = "NEW_READER";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (
            value.equals(REGISTERED) ||
            value.equals(INVITED_TO_CLUB) ||
            value.equals(MEMBERSHIP_REQUESTED) ||
            value.equals(MEMBERSHIP_DECLINED) ||
            value.equals(MEMBERSHIP_APPROVED) ||
            value.equals(MEMBERSHIP_OFFERED) ||
            value.equals(MEMBERSHIP_REJECTED) ||
            value.equals(MEMBERSHIP_ACCEPTED) ||
            value.equals(NEW_READER)
        );
    }
}
