package com.viraj.projectbackend.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 15)
    private String phone;

    @Column(length = 50)
    private String branch;

    @Column(name = "year_of_study")
    private Integer yearOfStudy;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;
}