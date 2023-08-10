package com.bahubba.bahubbabookclubreactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * Custom exception for when a client searches for a book club that doesn't exist (in an active state)
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Reader not found")
public class BookClubNotFoundException extends RuntimeException {

    /**
     * Generates exception for missing book club by name
     * @param name book club name
     */
    public BookClubNotFoundException(String name) {
        super("Book Club could not be found with name '" + name + "'");
    }

    /**
     * Generates exception for missing book club by ID
     * @param id book club ID
     */
    public BookClubNotFoundException(UUID id) {
        super("Book Club could not be found with ID '" + id + "'");
    }
}
