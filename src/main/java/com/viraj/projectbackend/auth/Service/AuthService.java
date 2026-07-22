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

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OtpEmailService otpEmailService;

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
    public String forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No account found with this email."));

        String otp = String.format("%06d", new Random().nextInt(1000000));

        user.setResetOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));

        userRepository.save(user);

        otpEmailService.sendOtp(user.getEmail(), otp);

        return "OTP has been sent to your email.";
    }
    public String verifyOtp(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getResetOtp() == null) {
            throw new RuntimeException("OTP not generated.");
        }

        if (!user.getResetOtp().equals(otp)) {
            throw new RuntimeException("Invalid OTP.");
        }

        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired.");
        }

        return "OTP verified.";
    }
    public String resetPassword(
            String email,
            String otp,
            String newPassword
    ) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getResetOtp() == null ||
                !user.getResetOtp().equals(otp)) {

            throw new RuntimeException("Invalid OTP.");
        }

        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired.");
        }

        user.setPassword(passwordEncoder.encode(newPassword));

        user.setResetOtp(null);
        user.setOtpExpiry(null);

        userRepository.save(user);

        return "Password updated successfully.";
    }

}