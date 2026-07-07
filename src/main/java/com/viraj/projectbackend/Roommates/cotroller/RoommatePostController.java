package com.viraj.projectbackend.Roommates.cotroller;




import com.viraj.projectbackend.Roommates.model.RoommatePost.RoommatePost;
import com.viraj.projectbackend.Roommates.service.RoommatePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roommate/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class RoommatePostController {

    @Autowired
    private RoommatePostService roommatePostService;

    // Get all roommate posts
    @GetMapping
    public List<RoommatePost> getAllPosts() {
        return roommatePostService.getAllPosts();
    }

    // Get roommate post by id
    @GetMapping("/{id}")
    public RoommatePost getPostById(@PathVariable Long id) {
        return roommatePostService.getPostById(id);
    }

    // Add roommate post
    @PostMapping
    public RoommatePost addPost(@RequestBody RoommatePost roommatePost) {
        return roommatePostService.addPost(roommatePost);
    }

    // Update roommate post
    @PutMapping("/{id}")
    public RoommatePost updatePost(
            @PathVariable Long id,
            @RequestBody RoommatePost roommatePost) {

        return roommatePostService.updatePost(id, roommatePost);
    }

    // Delete roommate post
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        roommatePostService.deletePost(id);
    }

}