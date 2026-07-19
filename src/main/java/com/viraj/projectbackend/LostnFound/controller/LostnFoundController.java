package com.viraj.projectbackend.LostnFound.controller;

import com.viraj.projectbackend.LostnFound.model.LostFound;
import com.viraj.projectbackend.LostnFound.service.LostFoundService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/lostfound")
@CrossOrigin(origins = "http://localhost:5173")
public class LostnFoundController {

    private final LostFoundService lostFoundService;

    public LostnFoundController(LostFoundService lostFoundService) {
        this.lostFoundService = lostFoundService;
    }

    // ================= GET ALL =================

    @GetMapping
    public List<LostFound> getAllItems() {
        return lostFoundService.getAllLostFoundItems();
    }

    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public LostFound getById(@PathVariable Long id) {
        return lostFoundService.getById(id);
    }

    // ================= CREATE =================

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LostFound addLostFound(

            @RequestParam String title,

            @RequestParam String status,

            @RequestParam String category,

            @RequestParam String location,

            @RequestParam String description,

            @RequestParam(required = false) MultipartFile image,

            @RequestParam String contactName,

            @RequestParam String contactEmail,

            @RequestParam Long userId

    ) throws IOException {

        return lostFoundService.addLostFound(
                title,
                status,
                category,
                location,
                description,
                image,
                contactName,
                contactEmail,
                userId
        );
    }

    // ================= UPDATE =================

    @PutMapping("/{id}")
    public LostFound updateLostFound(
            @PathVariable Long id,
            @RequestBody LostFound lostFound
    ) {

        return lostFoundService.updateLostFound(id, lostFound);

    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {

        lostFoundService.deleteItem(id);

    }
    // ================= CLAIM ITEM =================

    @PostMapping("/claim/{id}")
    public String claimItem(
            @PathVariable Long id,
            @RequestParam Long finderUserId
    ) {

        lostFoundService.claimItem(id, finderUserId);

        return "Claim request sent successfully!";
    }

}