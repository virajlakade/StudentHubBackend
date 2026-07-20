package com.viraj.projectbackend.auth.Service;

import com.viraj.projectbackend.auth.dto.JwtResponse;
import com.viraj.projectbackend.auth.dto.LoginRequest;
import com.viraj.projectbackend.auth.dto.RegisterRequest;
import com.viraj.projectbackend.auth.jwt.JwtService;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // ================= REGISTER =================

    public JwtResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .branch(request.getBranch())
                .yearOfStudy(request.getYearOfStudy())
                .rollNumber(request.getRollNumber())
                .degreeProgram(request.getDegreeProgram())
                .provider("LOCAL")
                .role("ROLE_USER")
                .enabled(true)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return JwtResponse.builder()
                .token(token)
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // ================= LOGIN =================

    public JwtResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());

        return JwtResponse.builder()
                .token(token)
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}