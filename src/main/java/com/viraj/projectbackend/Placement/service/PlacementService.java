package com.viraj.projectbackend.Placement.service;

import com.viraj.projectbackend.Placement.model.PlacementExperience;
import com.viraj.projectbackend.Placement.repo.PlacemnetRepo;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlacementService {

    private final PlacemnetRepo placemnetRepo;
    private final UserRepository userRepository;

    // ================= GET ALL =================

    public List<PlacementExperience> getAllExperiences() {
        return placemnetRepo.findAll();
    }

    // ================= GET BY ID =================

    public PlacementExperience getExperienceById(Long id) {
        return placemnetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));
    }

    // ================= ADD =================

    public PlacementExperience addExperience(PlacementExperience experience) {

        if (experience.getUser() == null || experience.getUser().getId() == null) {
            throw new RuntimeException("User Id is required.");
        }

        User user = userRepository.findById(experience.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        experience.setUser(user);

        return placemnetRepo.save(experience);
    }

    // ================= UPDATE =================

    public PlacementExperience updateExperience(Long id,
                                                PlacementExperience experience) {

        PlacementExperience existing = placemnetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        existing.setCompanyName(experience.getCompanyName());
        existing.setRole(experience.getRole());
        existing.setYear(experience.getYear());
        existing.setType(experience.getType());
        existing.setAuthorName(experience.getAuthorName());
        existing.setAuthorEmail(experience.getAuthorEmail());
        existing.setDifficulty(experience.getDifficulty());
        existing.setSelectionStatus(experience.getSelectionStatus());
        existing.setRounds(experience.getRounds());
        existing.setExperienceText(experience.getExperienceText());
        existing.setTips(experience.getTips());

        if (experience.getUser() != null &&
                experience.getUser().getId() != null) {

            User user = userRepository.findById(
                    experience.getUser().getId()
            ).orElseThrow(() -> new RuntimeException("User not found"));

            existing.setUser(user);
        }

        return placemnetRepo.save(existing);
    }

    // ================= DELETE =================

    public void deleteExperience(Long id) {
        placemnetRepo.deleteById(id);
    }

    // ================= LIKE =================

    public PlacementExperience likeExperience(Long id) {

        PlacementExperience experience = placemnetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found"));

        if (Boolean.TRUE.equals(experience.getLikedByUser())) {

            experience.setLikedByUser(false);
            experience.setLikes(Math.max(0, experience.getLikes() - 1));

        } else {

            experience.setLikedByUser(true);
            experience.setLikes(experience.getLikes() + 1);
        }

        return placemnetRepo.save(experience);
    }

}