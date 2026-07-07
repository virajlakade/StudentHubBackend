package com.viraj.projectbackend.Confessions.repo;



import com.viraj.projectbackend.Confessions.model.Confession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConfessionRepository
        extends JpaRepository<Confession, Long> {

    List<Confession> findByCategory(String category);

    List<Confession> findTop5ByOrderByLikesDesc();
    @Query("SELECT DISTINCT c.category FROM Confession c")
    List<String> findDistinctCategories();
}