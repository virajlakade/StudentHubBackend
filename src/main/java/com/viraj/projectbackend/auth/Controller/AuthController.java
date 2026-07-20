package com.viraj.projectbackend.auth.Controller;

import com.viraj.projectbackend.auth.dto.JwtResponse;
import com.viraj.projectbackend.auth.dto.LoginRequest;
import com.viraj.projectbackend.auth.dto.RegisterRequest;
import com.viraj.projectbackend.auth.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}