package com.viraj.projectbackend.service.Subject;

import com.viraj.projectbackend.Repo.Subject.SubjectRepository;
import com.viraj.projectbackend.model.Subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
