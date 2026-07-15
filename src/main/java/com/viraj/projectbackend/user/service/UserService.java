package com.viraj.projectbackend.user.service;


import com.viraj.projectbackend.user.repo.UserRepository;
import com.viraj.projectbackend.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));
    }

    // Get user by Email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found with email: " + email));
    }

    // Create User
    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists.");
        }

        return userRepository.save(user);
    }

    // Update User
    public User updateUser(Long id, User updatedUser) {

        User user = getUserById(id);

        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setBranch(updatedUser.getBranch());
        user.setYearOfStudy(updatedUser.getYearOfStudy());
        user.setProfileImage(updatedUser.getProfileImage());
        user.setBio(updatedUser.getBio());

        // Uncomment if password update is allowed
        // user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }

    // Delete User
    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }

}