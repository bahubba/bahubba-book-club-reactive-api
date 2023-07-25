package com.bahubba.bahubbabookclubreactive.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Reader (user) information to be returned to clients
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDTO {
    private UUID id;
    private String username;
    private String email;
    private String givenName;
    private String middleName;
    private String surname;
    private String suffix;
    private String title;
    private LocalDateTime joined;
    private LocalDateTime departed;
}
