package com.viraj.projectbackend.model.Confessions;



import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
@Builder

@Entity
@Table(name = "confession_comments")
public class ConfessionComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String commentText;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "confession_id")
    @JsonBackReference
    private Confession confession;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public ConfessionComment() {
    }

    // getters setters
}
