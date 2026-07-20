package com.viraj.projectbackend.auth.jwt;

import com.viraj.projectbackend.auth.Security.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {

            String jwt = extractToken(request);

            if (jwt != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                String email = jwtService.extractUsername(jwt);

                if (email != null) {

                    UserDetails userDetails =
                            customUserDetailsService.loadUserByUsername(email);

                    if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authentication.setDetails(
                                new WebAuthenticationDetailsSource()
                                        .buildDetails(request)
                        );

                        SecurityContextHolder.getContext()
                                .setAuthentication(authentication);
                    }
                }
            }

        } catch (JwtException | IllegalArgumentException e) {

            // Invalid token - continue request without authentication
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (!StringUtils.hasText(header)) {
            return null;
        }

        if (!header.startsWith("Bearer ")) {
            return null;
        }

        String token = header.substring(7).trim();

        if (!StringUtils.hasText(token)
                || token.equalsIgnoreCase("null")
                || token.equalsIgnoreCase("undefined")) {
            return null;
        }

        return token;
    }
}