package com.viraj.projectbackend.model.Placement;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "placement_experience")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlacementExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Optional: Foreign Key to User
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Integer year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlacementType type;

    @Column(nullable = false)
    private String authorName;

    @Column(nullable = false)
    private String authorEmail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SelectionStatus selectionStatus;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String rounds;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String experienceText;

    @Column(columnDefinition = "TEXT")
    private String tips;

    @Column(nullable = false)
    private Integer likes;

    @Column(nullable = false)
    private Boolean likedByUser;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        if (likes == null) {
            likes = 0;
        }

        if (likedByUser == null) {
            likedByUser = false;
        }
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}