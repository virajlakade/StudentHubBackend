package com.viraj.projectbackend.Confessions.model;

import com.viraj.projectbackend.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "confession_likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"confession_id", "user_id"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfessionLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confession_id", nullable = false)
    private Confession confession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}