package com.viraj.projectbackend.Confessions.service;

import com.viraj.projectbackend.Confessions.model.Confession;
import com.viraj.projectbackend.Confessions.model.ConfessionLike;
import com.viraj.projectbackend.Confessions.repo.ConfessionLikeRepository;
import com.viraj.projectbackend.Confessions.repo.ConfessionRepository;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfessionService {

    private final ConfessionRepository repository;
    private final ConfessionLikeRepository likeRepository;
    private final UserRepository userRepository;

    public ConfessionService(
            ConfessionRepository repository,
            ConfessionLikeRepository likeRepository,
            UserRepository userRepository) {

        this.repository = repository;
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
    }

    public List<Confession> getAll() {

        List<Confession> confessions = repository.findAll();

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication.getName().equals("anonymousUser")) {

            return confessions;
        }

        User user = userRepository.findByEmail(authentication.getName())
                .orElse(null);

        if (user == null) {
            return confessions;
        }

        for (Confession confession : confessions) {

            confession.setLiked(
                    likeRepository.existsByConfessionAndUser(
                            confession,
                            user
                    )
            );
        }

        return confessions;
    }

    public Confession save(Confession confession) {
        return repository.save(confession);
    }

    public Confession getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Confession> getTrending() {
        return repository.findTop5ByOrderByLikesDesc();
    }

    public Confession like(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Please login first.");
        }

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Confession confession = repository.findById(id)
                .orElseThrow();

        boolean alreadyLiked =
                likeRepository.existsByConfessionAndUser(confession, user);

        if (alreadyLiked) {
            return confession;
        }

        ConfessionLike like = ConfessionLike.builder()
                .confession(confession)
                .user(user)
                .build();

        likeRepository.save(like);

        confession.setLikes(
                (int) likeRepository.countByConfession(confession)
        );

        return repository.save(confession);
    }

    public List<String> getCategories() {
        return List.of(
                "Academic",
                "Hostel",
                "Relationship",
                "College Life",
                "Placement",
                "Funny",
                "Other"
        );
    }
}