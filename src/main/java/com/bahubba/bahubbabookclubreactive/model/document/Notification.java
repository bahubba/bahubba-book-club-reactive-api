package com.bahubba.bahubbabookclubreactive.model.document;

import com.bahubba.bahubbabookclubreactive.model.document.subdocument.NotificationView;
import com.bahubba.bahubbabookclubreactive.model.document.validator.NotificationTypeConstraint;
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

@Document(collection = "notifications")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    private UUID id;
    private Reader sourceReader;
    private Reader targetReader;
    private BookClub bookClub;
    @NotificationTypeConstraint
    private String type;
    private String actionLink;
    @CreatedDate
    private Date generated;
    private List<NotificationView> views;
}
