package com.viraj.projectbackend.LostnFound.repo;

import com.viraj.projectbackend.LostnFound.model.LostFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostFoundRepo extends JpaRepository<LostFound,Long> {




}
