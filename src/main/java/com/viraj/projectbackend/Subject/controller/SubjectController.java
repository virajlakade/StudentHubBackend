package com.viraj.projectbackend.Subject.controller;

import com.viraj.projectbackend.Subject.model.Subject;
import com.viraj.projectbackend.Subject.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    // Get all subjects of a user
    @GetMapping("/user/{userId}")
    public List<Subject> getSubjectsByUser(@PathVariable Long userId) {
        return subjectService.getSubjectsByUser(userId);
    }

    // Get one subject of a user
    @GetMapping("/user/{userId}/{id}")
    public Subject getSubjectById(
            @PathVariable Long userId,
            @PathVariable Long id) {

        return subjectService.getSubjectById(id, userId);
    }

    // Add subject for a user
    @PostMapping("/user/{userId}")
    public Subject addSubject(
            @PathVariable Long userId,
            @RequestBody Subject subject) {

        return subjectService.addSubject(userId, subject);
    }

    // Update subject of a user
    @PutMapping("/user/{userId}/{id}")
    public Subject updateSubject(
            @PathVariable Long userId,
            @PathVariable Long id,
            @RequestBody Subject subject) {

        return subjectService.updateSubject(id, userId, subject);
    }

    // Delete subject of a user
    @DeleteMapping("/user/{userId}/{id}")
    public void deleteSubject(
            @PathVariable Long userId,
            @PathVariable Long id) {

        subjectService.deleteSubject(id, userId);
    }
    @DeleteMapping("/user/{userId}/all")
    public void deleteAllSubjects(
            @PathVariable Long userId) {

        subjectService.deleteAllSubjects(userId);

    }
}