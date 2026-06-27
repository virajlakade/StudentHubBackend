package com.viraj.projectbackend.Repo.Confessions;



import com.viraj.projectbackend.model.Confessions.ConfessionComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<ConfessionComment, Long> {

    List<ConfessionComment> findByConfessionId(Long confessionId);
}