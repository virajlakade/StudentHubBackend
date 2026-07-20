package com.viraj.projectbackend.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viraj.projectbackend.Attendance.model.AttendanceLog;
import com.viraj.projectbackend.LostnFound.model.LostFound;
import com.viraj.projectbackend.Placement.model.PlacementExperience;
import com.viraj.projectbackend.Roommates.model.RoommatePost.RoommatePost;
import com.viraj.projectbackend.Roommates.model.RoommateRequest.RoommateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    // BCrypt encoded password.
    // Can be null for Google/GitHub users.
    @Column
    private String password;

    @Column(length = 15)
    private String phone;

    @Column(length = 100)
    private String branch;

    @Column(name = "year_of_study")
    private Integer yearOfStudy;

    @Column(name = "roll_number", length = 30)
    private String rollNumber;

    @Column(name = "degree_program", length = 100)
    private String degreeProgram;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Lob
    @Column(name = "profile_image", columnDefinition = "LONGTEXT")
    private String profileImage;

    @Column(columnDefinition = "TEXT")
    private String bio;

    // ================= Authentication =================

    @Column(nullable = false)
    @Builder.Default
    private String role = "ROLE_USER";

    @Column(nullable = false)
    @Builder.Default
    private Boolean enabled = true;

    // LOCAL / GOOGLE / GITHUB
    @Column(nullable = false)
    @Builder.Default
    private String provider = "LOCAL";

    // Google Subject ID / GitHub User ID
    private String providerId;

    // OAuth profile picture
    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

    // ================= Relationships =================

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AttendanceLog> attendanceLogs;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<LostFound> lostFoundPosts;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PlacementExperience> placementExperiences;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RoommatePost> roommatePosts;

    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<RoommateRequest> sentRequests;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<RoommateRequest> receivedRequests;
}