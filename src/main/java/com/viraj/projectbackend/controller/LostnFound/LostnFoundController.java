package com.viraj.projectbackend.controller.LostnFound;

import com.viraj.projectbackend.model.LostnFound.LostFound;
import com.viraj.projectbackend.service.LostnFound.LostFoundService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lostnfound")
@CrossOrigin(origins = "http://localhost:5173")
public class LostnFoundController {
    private LostFoundService lostFoundService;
    @GetMapping("/{id}")
    public LostFound getById(@PathVariable Long id) {
        return lostFoundService.getById(id);
    }

    // Add new item
    @PostMapping
    public LostFound addLostFound(@RequestBody LostFound lostFound) {
        return lostFoundService.addLostFound(lostFound);
    }

    // Update item
    @PutMapping("/{id}")
    public LostFound updateLostFound(
            @PathVariable Long id,
            @RequestBody LostFound lostFound) {

        return lostFoundService.updateLostFound(id, lostFound);
    }

    // Delete item
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        lostFoundService.deleteItem(id);
    }
}
