package com.viraj.projectbackend.Roommates.model.RoommatePost;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viraj.projectbackend.Roommates.model.RoommateRequest.RoommateRequest;
import com.viraj.projectbackend.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "roommate_posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RoommatePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({
            "hibernateLazyInitializer",
            "handler",
            "password",
            "attendanceLogs",
            "lostFoundPosts",
            "placementExperiences",
            "roommatePosts",
            "sentRequests",
            "receivedRequests"
    })
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Double rent;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String occupancy;

    @Column(nullable = false)
    private String status;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    @Column(columnDefinition = "LONGTEXT")
    private String avatar;

    private String branch;

    private Integer yearOfStudy;

    private String degreeProgram;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @JsonIgnore
    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<RoommateRequest> requests;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "roommate_post_tags",
            joinColumns = @JoinColumn(name = "post_id")
    )
    @Column(name = "tag")
    private List<String> tags;
}