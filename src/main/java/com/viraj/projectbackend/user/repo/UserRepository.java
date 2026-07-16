package com.viraj.projectbackend.user.repo;

import com.viraj.projectbackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByRollNumber(String rollNumber);

    boolean existsByRollNumber(String rollNumber);

}