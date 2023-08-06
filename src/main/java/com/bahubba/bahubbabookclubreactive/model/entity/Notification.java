package com.bahubba.bahubbabookclubreactive.model.entity;

import com.bahubba.bahubbabookclubreactive.model.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "notification")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_reader_id")
    private Reader sourceReader;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_reader_id")
    private Reader targetReader;

    @ManyToOne
    @JoinColumn(name = "book_club_id")
    private BookClub bookClub;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "action_link")
    private String actionLink;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime generated = LocalDateTime.now();

    @OneToMany(mappedBy = "notification", fetch = FetchType.LAZY)
    private Set<NotificationViews> views;
}
