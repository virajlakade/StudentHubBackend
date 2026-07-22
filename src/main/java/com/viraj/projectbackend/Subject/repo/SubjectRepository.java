package com.viraj.projectbackend.Subject.repo;

import com.viraj.projectbackend.Subject.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByUserId(Long userId);

    Subject findByIdAndUserId(Long id, Long userId);

    boolean existsByCodeAndUserId(String code, Long userId);

}