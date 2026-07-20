package com.viraj.projectbackend.auth.Service;

import com.viraj.projectbackend.auth.dto.JwtResponse;
import com.viraj.projectbackend.auth.dto.LoginRequest;
import com.viraj.projectbackend.auth.dto.RegisterRequest;
import com.viraj.projectbackend.auth.jwt.JwtService;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // ========================= REGISTER =========================

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
                .yearOfStudy(
                        request.getYearOfStudy() == null
                                ? 1
                                : request.getYearOfStudy()
                )
                .rollNumber(request.getRollNumber())
                .degreeProgram(request.getDegreeProgram())
                .provider("LOCAL")
                .enabled(true)
                .role("ROLE_USER")
                .build();

        user = userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail());

        return buildResponse(user, token);
    }

    // ========================= LOGIN =========================

    public JwtResponse login(LoginRequest request) {

        try {

            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )

            );

        } catch (Exception e) {

            throw new BadCredentialsException(
                    "Invalid email or password."
            );

        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        String token = jwtService.generateToken(user.getEmail());

        return buildResponse(user, token);
    }

    // ========================= COMMON RESPONSE =========================

    private JwtResponse buildResponse(
            User user,
            String token
    ) {

        return JwtResponse.builder()
                .token(token)
                .type("Bearer")
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}