package com.viraj.projectbackend.service;

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

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Subject subject) {
        subject.setId(id);
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}