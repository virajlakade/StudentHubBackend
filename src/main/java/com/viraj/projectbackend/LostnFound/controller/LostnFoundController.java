package com.viraj.projectbackend.LostnFound.controller;

import com.viraj.projectbackend.LostnFound.model.LostFound;
import com.viraj.projectbackend.LostnFound.service.LostFoundService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lostfound")
@CrossOrigin(origins = "http://localhost:5173")
public class LostnFoundController {

    private final LostFoundService lostFoundService;

    public LostnFoundController(LostFoundService lostFoundService) {
        this.lostFoundService = lostFoundService;
    }

    @GetMapping
    public List<LostFound> getAllItems() {
        return lostFoundService.getAllLostFoundItems();
    }

    @GetMapping("/{id}")
    public LostFound getById(@PathVariable Long id) {
        return lostFoundService.getById(id);
    }

    @PostMapping
    public LostFound addLostFound(@RequestBody LostFound lostFound) {
        return lostFoundService.addLostFound(lostFound);
    }

    @PutMapping("/{id}")
    public LostFound updateLostFound(
            @PathVariable Long id,
            @RequestBody LostFound lostFound) {

        return lostFoundService.updateLostFound(id, lostFound);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        lostFoundService.deleteItem(id);
    }
}