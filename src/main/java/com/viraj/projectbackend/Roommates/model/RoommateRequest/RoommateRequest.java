package com.viraj.projectbackend.Roommates.model.RoommateRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viraj.projectbackend.Roommates.model.RoommatePost.RoommatePost;
import com.viraj.projectbackend.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "roommate_request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoommateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnoreProperties({"requests"})
    private RoommatePost post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    @JsonIgnoreProperties({
            "password",
            "attendanceLogs",
            "lostFoundPosts",
            "placementExperiences",
            "roommatePosts",
            "sentRequests",
            "receivedRequests"
    })
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    @JsonIgnoreProperties({
            "password",
            "attendanceLogs",
            "lostFoundPosts",
            "placementExperiences",
            "roommatePosts",
            "sentRequests",
            "receivedRequests"
    })
    private User receiver;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}