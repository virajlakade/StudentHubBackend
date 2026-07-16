package com.viraj.projectbackend.Roommates.service;

import com.viraj.projectbackend.Roommates.model.RoommatePost.RoommatePost;
import com.viraj.projectbackend.Roommates.repo.RoommatepostRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoommatePostService {

    private final RoommatepostRepo roommatepostRepo;

    // ================= GET ALL =================

    public List<RoommatePost> getAllPosts() {
        return roommatepostRepo.findAll();
    }

    // ================= GET BY ID =================

    public RoommatePost getPostById(Long id) {
        return roommatepostRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Roommate post not found"));
    }

    // ================= CREATE =================

    public RoommatePost createPost(RoommatePost roommatePost) {

        roommatePost.setId(null);

        if (roommatePost.getStatus() == null ||
                roommatePost.getStatus().isBlank()) {

            roommatePost.setStatus("OPEN");
        }

        return roommatepostRepo.save(roommatePost);
    }

    // ================= UPDATE =================

    public RoommatePost updatePost(RoommatePost roommatePost) {

        RoommatePost existing =
                roommatepostRepo.findById(roommatePost.getId())
                        .orElseThrow(() ->
                                new RuntimeException("Roommate post not found"));

        existing.setTitle(roommatePost.getTitle());
        existing.setDescription(roommatePost.getDescription());
        existing.setLocation(roommatePost.getLocation());
        existing.setRent(roommatePost.getRent());
        existing.setGender(roommatePost.getGender());
        existing.setOccupancy(roommatePost.getOccupancy());
        existing.setStatus(roommatePost.getStatus());

        existing.setContactName(roommatePost.getContactName());
        existing.setContactEmail(roommatePost.getContactEmail());
        existing.setContactPhone(roommatePost.getContactPhone());

        existing.setAvatar(roommatePost.getAvatar());

        existing.setBranch(roommatePost.getBranch());
        existing.setYearOfStudy(roommatePost.getYearOfStudy());
        existing.setDegreeProgram(roommatePost.getDegreeProgram());

        existing.setUser(roommatePost.getUser());

        return roommatepostRepo.save(existing);
    }

    // ================= DELETE =================

    public void deletePost(Long id) {

        roommatepostRepo.deleteById(id);

    }

}