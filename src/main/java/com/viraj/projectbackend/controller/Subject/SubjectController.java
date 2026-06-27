package com.viraj.projectbackend.controller.Subject;

import com.viraj.projectbackend.model.Subject.Subject;
import com.viraj.projectbackend.service.Subject.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "http://localhost:5173")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/getSubjects")
    public List<Subject> getSubjects() {
        return subjectService.getAllSubjects();
    }
}
