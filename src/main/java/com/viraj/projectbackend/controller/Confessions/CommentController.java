package com.viraj.projectbackend.controller.Confessions;


import com.viraj.projectbackend.model.Confessions.Confession;
import com.viraj.projectbackend.model.Confessions.ConfessionComment;
import com.viraj.projectbackend.Repo.Confessions.CommentRepository;
import com.viraj.projectbackend.Repo.Confessions.ConfessionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/comments")

public class CommentController {

    private final CommentRepository commentRepository;
    private final ConfessionRepository confessionRepository;

    public CommentController(
            CommentRepository commentRepository,
            ConfessionRepository confessionRepository) {

        this.commentRepository = commentRepository;
        this.confessionRepository = confessionRepository;
    }

    @GetMapping("/{confessionId}")
    public List<ConfessionComment> getComments(
            @PathVariable Long confessionId) {

        return commentRepository.findByConfessionId(confessionId);
    }

    @PostMapping("/{confessionId}")
    public ConfessionComment addComment(
            @PathVariable Long confessionId,
            @RequestBody ConfessionComment comment) {

        Confession confession =
                confessionRepository.findById(confessionId)
                        .orElseThrow();

        comment.setConfession(confession);

        return commentRepository.save(comment);
    }
    @GetMapping("/categories")
    public List<String> getCategories() {
        CommentController confessionService = null;
        return confessionService.getCategories();
    }
}