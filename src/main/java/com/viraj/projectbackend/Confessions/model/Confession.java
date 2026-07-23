package com.viraj.projectbackend.Confessions.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "confessions")
public class Confession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String category;

    private int likes;

    private LocalDateTime createdAt;

    @Transient
    private boolean liked;

    @OneToMany(
            mappedBy = "confession",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<ConfessionComment> comments;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}