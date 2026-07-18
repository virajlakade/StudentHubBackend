package com.viraj.projectbackend.user.service;

import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ================= CREATE USER =================

    public User createUser(User user) {
        return userRepository.save(user);
    }

    // ================= GET USER BY ID =================

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));
    }

    // ================= GET USER BY EMAIL =================

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found with email: " + email));
    }

    // ================= GET ALL USERS =================

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ================= UPDATE PROFILE =================

    public User updateProfile(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));

        user.setFullName(updatedUser.getFullName());
        user.setPhone(updatedUser.getPhone());
        user.setBranch(updatedUser.getBranch());
        user.setYearOfStudy(updatedUser.getYearOfStudy());
        user.setRollNumber(updatedUser.getRollNumber());
        user.setDegreeProgram(updatedUser.getDegreeProgram());
        user.setSkills(updatedUser.getSkills());
        user.setBio(updatedUser.getBio());

        if (updatedUser.getProfileImage() != null &&
                !updatedUser.getProfileImage().isEmpty()) {
            user.setProfileImage(updatedUser.getProfileImage());
        }

        return userRepository.save(user);
    }

    // ================= DELETE USER =================

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));

        userRepository.delete(user);
    }
}