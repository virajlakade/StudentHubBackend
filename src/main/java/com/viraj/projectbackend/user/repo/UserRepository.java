package com.viraj.projectbackend.user.repo;

import com.viraj.projectbackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Find user by roll number
    Optional<User> findByRollNumber(String rollNumber);

    // Check if roll number already exists
    boolean existsByRollNumber(String rollNumber);

}