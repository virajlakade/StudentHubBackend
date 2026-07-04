package com.viraj.projectbackend.service.LostnFound;

import com.viraj.projectbackend.Repo.LostnFound.LostFoundRepo;
import com.viraj.projectbackend.model.LostnFound.LostFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostFoundService {

    @Autowired
    private LostFoundRepo lostFoundRepo;

    // Get all items
    public List<LostFound> getAllLostFoundItems() {
        return lostFoundRepo.findAll();
    }

    // Get item by id
    public LostFound getById(Long id) {
        return lostFoundRepo.findById(id).orElse(null);
    }

    // Add item
    public LostFound addLostFound(LostFound lostFound) {
        return lostFoundRepo.save(lostFound);
    }

    // Update item
    public LostFound updateLostFound(Long id, LostFound lostFound) {
        lostFound.setId(id);
        return lostFoundRepo.save(lostFound);
    }

    // Delete item
    public void deleteItem(Long id) {
        lostFoundRepo.deleteById(id);
    }
}