package com.bahubba.bahubbabookclubreactive.model.entity;

import com.bahubba.bahubbabookclubreactive.model.enums.Publicity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Book Clubs, or discussion groups for books, which can be created, joined, managed, etc. Heart of the application
 */
@Entity
@Table(name = "book_club")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookClub implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "image_url")
    private String imageURL;

    @Column(nullable = false)
    @Builder.Default
    private String description = "A book club for reading books!";

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Publicity publicity = Publicity.PRIVATE;

    @OneToMany(mappedBy = "bookClub", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private Set<BookClubMembership> members;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

    @Column
    private LocalDateTime disbanded;
}
