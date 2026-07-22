package com.viraj.projectbackend.Subject.service;

import com.viraj.projectbackend.Subject.model.Subject;
import com.viraj.projectbackend.Subject.repo.SubjectRepository;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public List<Subject> getSubjectsByUser(Long userId) {
        return subjectRepository.findByUserId(userId);
    }

    public Subject getSubjectById(Long id, Long userId) {
        return subjectRepository.findByIdAndUserId(id, userId);
    }

    public Subject addSubject(Long userId, Subject subject) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (subjectRepository.existsByCodeAndUserId(subject.getCode(), userId)) {
            throw new RuntimeException("Subject code already exists.");
        }

        subject.setUser(user);

        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Long userId, Subject updatedSubject) {

        Subject existing = subjectRepository.findByIdAndUserId(id, userId);

        if (existing == null) {
            throw new RuntimeException("Subject not found");
        }

        existing.setName(updatedSubject.getName());
        existing.setCode(updatedSubject.getCode());
        existing.setInstructor(updatedSubject.getInstructor());
        existing.setRoom(updatedSubject.getRoom());
        existing.setTargetPercentage(updatedSubject.getTargetPercentage());

        return subjectRepository.save(existing);
    }

    public void deleteSubject(Long id, Long userId) {

        Subject subject = subjectRepository.findByIdAndUserId(id, userId);

        if (subject == null) {
            throw new RuntimeException("Subject not found");
        }

        subjectRepository.delete(subject);
    }

    @Transactional
    public void deleteAllSubjects(Long userId) {

        List<Subject> subjects = subjectRepository.findByUserId(userId);

        subjectRepository.deleteAll(subjects);
    }
}