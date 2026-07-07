package com.viraj.projectbackend.Placement.service;



import com.viraj.projectbackend.Placement.repo.PlacemnetRepo;
import com.viraj.projectbackend.Placement.model.PlacementExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementService {

    @Autowired
    private PlacemnetRepo placemnetRepo;

    // Get all experiences
    public List<PlacementExperience> getAllExperiences() {
        return placemnetRepo.findAll();
    }

    // Get experience by id
    public PlacementExperience getExperienceById(Long id) {
        return placemnetRepo.findById(id).orElse(null);
    }

    // Add experience
    public PlacementExperience addExperience(PlacementExperience experience) {
        return placemnetRepo.save(experience);
    }

    // Update experience
    public PlacementExperience updateExperience(Long id, PlacementExperience experience) {
        experience.setId(id);
        return placemnetRepo.save(experience);
    }

    // Delete experience
    public void deleteExperience(Long id) {
        placemnetRepo.deleteById(id);
    }

    // Like / Unlike
    public PlacementExperience likeExperience(Long id) {

        PlacementExperience experience =
                placemnetRepo.findById(id).orElse(null);

        if (experience == null) {
            return null;
        }

        if (experience.getLikedByUser()) {
            experience.setLikedByUser(false);
            experience.setLikes(Math.max(0, experience.getLikes() - 1));
        } else {
            experience.setLikedByUser(true);
            experience.setLikes(experience.getLikes() + 1);
        }

        return placemnetRepo.save(experience);
    }
}
