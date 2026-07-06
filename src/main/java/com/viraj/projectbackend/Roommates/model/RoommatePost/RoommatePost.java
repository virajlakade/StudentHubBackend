package com.viraj.projectbackend.Roommates.model.RoommatePost;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "roommate_post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoommatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key (later)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer rent;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "roommate_post_tags",
            joinColumns = @JoinColumn(name = "post_id")
    )
    @Column(name = "tag")
    private List<String> tags;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GenderPreference gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    private String avatar;

    private String degree;

    private String year;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}