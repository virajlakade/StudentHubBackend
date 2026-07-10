package com.viraj.projectbackend.Roommates.service;



import com.viraj.projectbackend.Roommates.model.RoommatePost.PostStatus;
import com.viraj.projectbackend.Roommates.model.RoommatePost.RoommatePost;
import com.viraj.projectbackend.Roommates.repo.RoommatepostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoommatePostService {

    @Autowired
    private RoommatepostRepo roommatepostRepo;

    // Get all posts
    public List<RoommatePost> getAllPosts() {
        return roommatepostRepo.findAll();
    }

    // Get post by id
    public RoommatePost getPostById(Long id) {
        return roommatepostRepo.findById(id).orElse(null);
    }

    // Add new post
    public RoommatePost addPost(RoommatePost roommatePost) {
        roommatePost.setStatus(PostStatus.OPEN);
        return roommatepostRepo.save(roommatePost);
    }

    // Update post
    public RoommatePost updatePost(Long id, RoommatePost roommatePost) {
        roommatePost.setId(id);
        return roommatepostRepo.save(roommatePost);
    }

    // Delete post
    public void deletePost(Long id) {
        roommatepostRepo.deleteById(id);
    }
}
