package com.viraj.projectbackend.Repo.Placement;

import com.viraj.projectbackend.model.Placement.PlacementExperience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacemnetRepo extends JpaRepository<PlacementExperience,Long> {
}
