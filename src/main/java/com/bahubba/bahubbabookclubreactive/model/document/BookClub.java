package com.bahubba.bahubbabookclubreactive.model.document;

import com.bahubba.bahubbabookclubreactive.model.document.subdocument.BookClubMember;
import com.bahubba.bahubbabookclubreactive.model.document.validator.PublicityConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "bookClubs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookClub {
    @Id
    private UUID id;
    private String name;
    private String description = "A book club for reading books";
    private String imageURL;
    @PublicityConstraint
    private String publicity;
    private List<BookClubMember> members;
    @CreatedDate
    private Date created;
    private Date disbanded;
}
