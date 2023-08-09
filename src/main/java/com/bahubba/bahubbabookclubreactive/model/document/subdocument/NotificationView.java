package com.bahubba.bahubbabookclubreactive.model.document.subdocument;

import com.bahubba.bahubbabookclubreactive.model.document.Reader;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationView {
    private Reader reader;
    @CreatedDate
    private Date viewed;
}
