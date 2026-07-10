package com.viraj.projectbackend.Roommates.repo;

import com.viraj.projectbackend.Roommates.model.RoommateRequest.RoommateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoommateRequestRepo extends JpaRepository<RoommateRequest,Long> {
}
