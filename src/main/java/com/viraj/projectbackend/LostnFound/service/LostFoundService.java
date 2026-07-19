package com.viraj.projectbackend.LostnFound.service;
import org.springframework.transaction.annotation.Transactional;
import com.viraj.projectbackend.LostnFound.model.LostFound;
import com.viraj.projectbackend.LostnFound.model.LostFoundStatus;
import com.viraj.projectbackend.LostnFound.repo.LostFoundRepo;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class LostFoundService {

    @Autowired
    private LostFoundRepo lostFoundRepo;
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    private static final String UPLOAD_DIR = "uploads/lostfound/";

    // ================= GET ALL =================

    public List<LostFound> getAllLostFoundItems() {
        return lostFoundRepo.findAll();
    }

    // ================= GET BY ID =================

    public LostFound getById(Long id) {
        return lostFoundRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // ================= CREATE =================

    public LostFound addLostFound(
            String title,
            String status,
            String category,
            String location,
            String description,
            MultipartFile image,
            String contactName,
            String contactEmail,
            Long userId
    ) throws IOException {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LostFound lostFound = new LostFound();

        lostFound.setTitle(title);
        lostFound.setStatus(LostFoundStatus.valueOf(status.toUpperCase()));
        lostFound.setCategory(category);
        lostFound.setLocation(location);
        lostFound.setDescription(description);
        lostFound.setContactName(contactName);
        lostFound.setContactEmail(contactEmail);
        lostFound.setUser(user);

        if (image != null && !image.isEmpty()) {

            File folder = new File(UPLOAD_DIR);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();

            Path path = Paths.get(UPLOAD_DIR, fileName);

            Files.copy(
                    image.getInputStream(),
                    path,
                    StandardCopyOption.REPLACE_EXISTING
            );

            lostFound.setImage("/uploads/lostfound/" + fileName);
        }

        return lostFoundRepo.save(lostFound);
    }

    // ================= UPDATE =================

    public LostFound updateLostFound(Long id, LostFound lostFound) {

        LostFound existing = getById(id);

        existing.setTitle(lostFound.getTitle());
        existing.setStatus(lostFound.getStatus());
        existing.setCategory(lostFound.getCategory());
        existing.setLocation(lostFound.getLocation());
        existing.setDescription(lostFound.getDescription());
        existing.setImage(lostFound.getImage());
        existing.setContactName(lostFound.getContactName());
        existing.setContactEmail(lostFound.getContactEmail());

        return lostFoundRepo.save(existing);
    }

    // ================= DELETE =================

    public void deleteItem(Long id) {

        lostFoundRepo.deleteById(id);

    }
    @Transactional
    public void claimItem(Long itemId, Long finderUserId) {

        LostFound item = lostFoundRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        User finder = userRepository.findById(finderUserId)
                .orElseThrow(() -> new RuntimeException("Finder not found"));

        emailService.sendLostFoundMail(
                item.getContactEmail(),
                item.getTitle(),
                finder.getFullName(),
                finder.getEmail(),
                finder.getPhone(),
                "I believe I have found your item. Please contact me."
        );
    }

}