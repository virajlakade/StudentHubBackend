package com.viraj.projectbackend.Placement.repo;

import com.viraj.projectbackend.Placement.model.PlacementExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacemnetRepo extends JpaRepository<PlacementExperience,Long> {
}
