package com.viraj.projectbackend.user.controller;

import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    // ================= GET CURRENT USER =================

    @GetMapping("/me")
    public User getCurrentUser(Authentication authentication) {

        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("User not authenticated");
        }

        return userService.getUserByEmail(authentication.getName());
    }

    // ================= GET ALL USERS =================

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ================= GET USER BY ID =================

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ================= GET USER BY EMAIL =================

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // ================= CREATE USER =================

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // ================= UPDATE PROFILE =================

    @PutMapping("/{id}")
    public User updateProfile(
            @PathVariable Long id,
            @RequestBody User updatedUser
    ) {
        return userService.updateProfile(id, updatedUser);
    }

    // ================= DELETE USER =================

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}