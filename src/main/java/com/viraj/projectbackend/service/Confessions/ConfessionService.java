package com.viraj.projectbackend.service.Confessions;





import com.viraj.projectbackend.Repo.Confessions.ConfessionRepository;
import com.viraj.projectbackend.model.Confessions.Confession;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfessionService {

    private final ConfessionRepository repository;

    public ConfessionService(ConfessionRepository repository) {
        this.repository = repository;
    }

    public List<Confession> getAll() {
        return repository.findAll();
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
        Confession c = repository.findById(id).orElseThrow();

        c.setLikes(c.getLikes() + 1);

        return repository.save(c);
    }
    public List<String> getCategories() {
        return repository.findDistinctCategories();
    }
}

