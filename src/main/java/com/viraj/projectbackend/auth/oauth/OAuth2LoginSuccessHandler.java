package com.viraj.projectbackend.auth.oauth;

import com.viraj.projectbackend.auth.jwt.JwtService;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        OAuth2AuthenticationToken authenticationToken =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = authenticationToken.getPrincipal();

        String provider =
                authenticationToken.getAuthorizedClientRegistrationId()
                        .toUpperCase();

        String email = null;
        String fullName = null;
        String providerId = null;
        String imageUrl = null;

        if ("GOOGLE".equals(provider)) {

            email = oauthUser.getAttribute("email");
            fullName = oauthUser.getAttribute("name");
            providerId = oauthUser.getAttribute("sub");
            imageUrl = oauthUser.getAttribute("picture");

        } else if ("GITHUB".equals(provider)) {

            providerId = String.valueOf(oauthUser.getAttribute("id"));

            fullName = oauthUser.getAttribute("name");

            if (fullName == null || fullName.isBlank()) {
                fullName = oauthUser.getAttribute("login");
            }

            email = oauthUser.getAttribute("email");

            if (email == null || email.isBlank()) {
                email = providerId + "@github.local";
            }

            imageUrl = oauthUser.getAttribute("avatar_url");
        }

        User user = userRepository
                .findByEmail(email)
                .orElseGet(User::new);

        if (user.getId() == null) {
            user.setEmail(email);
            user.setPassword("");
        }

        user.setFullName(fullName);
        user.setProvider(provider);
        user.setProviderId(providerId);
        user.setImageUrl(imageUrl);
        user.setEnabled(true);

        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        user = userRepository.save(user);

        String jwt = jwtService.generateToken(user.getEmail());

        String redirectUrl =
                "http://localhost:5173/?token="
                        + URLEncoder.encode(jwt, StandardCharsets.UTF_8);

        response.sendRedirect(redirectUrl);
    }
}