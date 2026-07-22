package com.viraj.projectbackend.LostnFound.service;

import com.viraj.projectbackend.LostnFound.model.LostFound;
import com.viraj.projectbackend.LostnFound.repo.LostFoundRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LostFoundCleanupService {

    private final LostFoundRepo lostFoundRepo;

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeExpiredPosts() {

        LocalDateTime expiryDate = LocalDateTime.now().minusDays(5);

        List<LostFound> expiredPosts =
                lostFoundRepo.findByCreatedAtBefore(expiryDate);

        if (!expiredPosts.isEmpty()) {

            lostFoundRepo.deleteAll(expiredPosts);

            System.out.println(
                    "Deleted " + expiredPosts.size() + " expired Lost & Found posts."
            );
        }
    }
}