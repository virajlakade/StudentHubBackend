package com.viraj.projectbackend.Roommates.cotroller;




import com.viraj.projectbackend.Roommates.model.RequestStatus;
import com.viraj.projectbackend.Roommates.model.RoommateRequest;
import com.viraj.projectbackend.Roommates.service.RoommateRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roommate/requests")
@CrossOrigin(origins = "http://localhost:5173")
public class RoommateRequestController {

    @Autowired
    private RoommateRequestService roommateRequestService;

    // Get all requests
    @GetMapping
    public List<RoommateRequest> getAllRequests() {
        return roommateRequestService.getAllRequests();
    }

    // Get request by id
    @GetMapping("/{id}")
    public RoommateRequest getRequestById(@PathVariable Long id) {
        return roommateRequestService.getRequestById(id);
    }

    // Send connection request
    @PostMapping
    public RoommateRequest sendRequest(
            @RequestBody RoommateRequest request) {

        return roommateRequestService.sendRequest(request);
    }

    // Update request status
    @PutMapping("/{id}/{status}")
    public RoommateRequest updateStatus(
            @PathVariable Long id,
            @PathVariable RequestStatus status) {

        return roommateRequestService.updateRequestStatus(id,              status);
    }

    // Delete request
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Long id) {
        roommateRequestService.deleteRequest(id);
    }

}