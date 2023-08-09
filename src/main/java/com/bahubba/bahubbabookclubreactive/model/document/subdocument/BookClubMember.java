package com.bahubba.bahubbabookclubreactive.model.document.subdocument;

import com.bahubba.bahubbabookclubreactive.model.document.Reader;
import com.bahubba.bahubbabookclubreactive.model.document.validator.BookClubRoleConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookClubMember {
    private Reader reader;
    @BookClubRoleConstraint
    private String role;
    @CreatedDate
    private String joined;
    private String departed;
}
