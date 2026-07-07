package com.viraj.projectbackend.Confessions.Controller;



import com.viraj.projectbackend.Confessions.model.Confession;
import com.viraj.projectbackend.Confessions.service.ConfessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/confessions")
@CrossOrigin(origins = "http://localhost:5173")
public class ConfessionController {

    private final ConfessionService service;

    public ConfessionController(ConfessionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Confession> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Confession create(
            @RequestBody Confession confession) {

        return service.save(confession);
    }

    @GetMapping("/{id}")
    public Confession getOne(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @PutMapping("/{id}/like")
    public Confession like(
            @PathVariable Long id) {

        return service.like(id);
    }

    @GetMapping("/trending")
    public List<Confession> trending() {
        return service.getTrending();
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return service.getCategories();
    }
}
