package com.viraj.projectbackend.auth.Controller;

import com.viraj.projectbackend.auth.Service.AuthService;
import com.viraj.projectbackend.auth.dto.JwtResponse;
import com.viraj.projectbackend.auth.dto.LoginRequest;
import com.viraj.projectbackend.auth.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    // ================= REGISTER =================

    @PostMapping("/register")
    public JwtResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    // ================= LOGIN =================

    @PostMapping("/login")
    public JwtResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }

    // ================= FORGOT PASSWORD =================

    @PostMapping("/forgot-password")
    public String forgotPassword(
            @RequestBody Map<String, String> request
    ) {

        return authService.forgotPassword(
                request.get("email")
        );
    }

    // ================= VERIFY OTP =================

    @PostMapping("/verify-otp")
    public String verifyOtp(
            @RequestBody Map<String, String> request
    ) {

        return authService.verifyOtp(
                request.get("email"),
                request.get("otp")
        );
    }

    // ================= RESET PASSWORD =================

    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody Map<String, String> request
    ) {

        return authService.resetPassword(
                request.get("email"),
                request.get("otp"),
                request.get("newPassword")
        );
    }
}