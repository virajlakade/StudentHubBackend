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
import java.util.Map;
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

        OAuth2AuthenticationToken token =
                (OAuth2AuthenticationToken) authentication;

        OAuth2User oauthUser = token.getPrincipal();

        String provider = token.getAuthorizedClientRegistrationId().toUpperCase();

        String email = null;
        String name = null;
        String providerId = null;
        String imageUrl = null;

        if ("GOOGLE".equals(provider)) {

            email = oauthUser.getAttribute("email");
            name = oauthUser.getAttribute("name");
            providerId = oauthUser.getAttribute("sub");
            imageUrl = oauthUser.getAttribute("picture");

        } else if ("GITHUB".equals(provider)) {

            providerId = String.valueOf(oauthUser.getAttribute("id"));
            name = oauthUser.getAttribute("name");

            if (name == null) {
                name = oauthUser.getAttribute("login");
            }

            email = oauthUser.getAttribute("email");

            imageUrl = oauthUser.getAttribute("avatar_url");

            if (email == null) {
                email = providerId + "@github.local";
            }
        }

        Optional<User> optionalUser = userRepository.findByEmail(email);

        User user;

        if (optionalUser.isPresent()) {

            user = optionalUser.get();

        } else {

            user = new User();

            user.setEmail(email);
            user.setPassword("");
        }

        user.setFullName(name);
        user.setProvider(provider);
        user.setProviderId(providerId);
        user.setImageUrl(imageUrl);
        user.setEnabled(true);

        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        userRepository.save(user);

        String jwt = jwtService.generateToken(user.getEmail());

        response.sendRedirect(
                "http://localhost:5173/oauth-success?token=" + jwt
        );
    }
}