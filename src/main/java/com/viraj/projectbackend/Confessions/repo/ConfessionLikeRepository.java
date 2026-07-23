package com.viraj.projectbackend.Confessions.repo;

import com.viraj.projectbackend.Confessions.model.Confession;
import com.viraj.projectbackend.Confessions.model.ConfessionLike;
import com.viraj.projectbackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfessionLikeRepository
        extends JpaRepository<ConfessionLike, Long> {

    Optional<ConfessionLike> findByConfessionAndUser(
            Confession confession,
            User user
    );

    boolean existsByConfessionAndUser(
            Confession confession,
            User user
    );

    long countByConfession(Confession confession);

    void deleteByConfessionAndUser(
            Confession confession,
            User user
    );
}