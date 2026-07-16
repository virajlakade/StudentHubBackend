package com.viraj.projectbackend.Roommates.cotroller;

import com.viraj.projectbackend.Roommates.model.RoommatePost.RoommatePost;
import com.viraj.projectbackend.Roommates.service.RoommatePostService;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roommate/posts")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class RoommateController {

    private final RoommatePostService roommatePostService;
    private final UserRepository userRepository;

    // ================= GET ALL =================

    @GetMapping
    public List<RoommatePost> getAllPosts() {
        return roommatePostService.getAllPosts();
    }

    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public RoommatePost getPostById(@PathVariable Long id) {
        return roommatePostService.getPostById(id);
    }

    // ================= CREATE =================

    @PostMapping
    public RoommatePost createPost(@RequestBody RoommatePost roommatePost) {

        if (roommatePost.getUser() != null &&
                roommatePost.getUser().getId() != null) {

            User user = userRepository.findById(
                    roommatePost.getUser().getId()
            ).orElseThrow(() ->
                    new RuntimeException("User not found"));

            roommatePost.setUser(user);

            roommatePost.setContactName(user.getFullName());
            roommatePost.setContactEmail(user.getEmail());
            roommatePost.setContactPhone(user.getPhone());

            roommatePost.setAvatar(user.getProfileImage());

            roommatePost.setBranch(user.getBranch());
            roommatePost.setYearOfStudy(user.getYearOfStudy());
            roommatePost.setDegreeProgram(user.getDegreeProgram());

            // Remove these two lines if User doesn't have skills
            // roommatePost.setSkills(user.getSkills());
        }

        return roommatePostService.createPost(roommatePost);
    }

    // ================= UPDATE =================

    @PutMapping("/{id}")
    public RoommatePost updatePost(
            @PathVariable Long id,
            @RequestBody RoommatePost roommatePost
    ) {

        roommatePost.setId(id);

        if (roommatePost.getUser() != null &&
                roommatePost.getUser().getId() != null) {

            User user = userRepository.findById(
                    roommatePost.getUser().getId()
            ).orElseThrow(() ->
                    new RuntimeException("User not found"));

            roommatePost.setUser(user);
        }

        return roommatePostService.updatePost(roommatePost);
    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        roommatePostService.deletePost(id);
    }

}