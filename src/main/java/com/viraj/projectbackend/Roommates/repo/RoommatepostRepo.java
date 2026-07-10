package com.viraj.projectbackend.Roommates.repo;

import com.viraj.projectbackend.Roommates.model.RoommatePost.RoommatePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoommatepostRepo extends JpaRepository<RoommatePost,Long> {
}
