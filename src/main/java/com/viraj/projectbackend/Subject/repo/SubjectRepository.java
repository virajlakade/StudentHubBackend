package com.viraj.projectbackend.Subject.repo;

import com.viraj.projectbackend.Subject.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    // Get all subjects of a particular user
    List<Subject> findByUserId(Long userId);

    // Optional: Find a subject by user and id
    Subject findByIdAndUserId(Long id, Long userId);

    // Optional: Prevent duplicate subject codes for the same user
    boolean existsByCodeAndUserId(String code, Long userId);
    void deleteByUserId(Long userId);
}