package com.viraj.projectbackend.Placement.controller;



import com.viraj.projectbackend.Placement.model.PlacementExperience;

import com.viraj.projectbackend.Placement.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/placements")
@CrossOrigin(origins = "http://localhost:5173")
public class PlacementController {

    @Autowired
    private PlacementService placementService;

    // GET ALL
    @GetMapping
    public List<PlacementExperience> getAllExperiences() {
        return placementService.getAllExperiences();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PlacementExperience getExperienceById(@PathVariable Long id) {
        return placementService.getExperienceById(id);
    }

    // ADD
    @PostMapping
    public PlacementExperience addExperience(
            @RequestBody PlacementExperience experience) {

        return placementService.addExperience(experience);
    }

    // UPDATE
    @PutMapping("/{id}")
    public PlacementExperience updateExperience(
            @PathVariable Long id,
            @RequestBody PlacementExperience experience) {

        return placementService.updateExperience(id, experience);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteExperience(@PathVariable Long id) {
        placementService.deleteExperience(id);
    }

    // LIKE / UNLIKE
    @PutMapping("/{id}/like")
    public PlacementExperience likeExperience(
            @PathVariable Long id) {

        return placementService.likeExperience(id);
    }
}
