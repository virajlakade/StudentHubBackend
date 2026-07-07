package com.viraj.projectbackend.Confessions.repo;



import com.viraj.projectbackend.Confessions.model.ConfessionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<ConfessionComment, Long> {

    List<ConfessionComment> findByConfessionId(Long confessionId);
}