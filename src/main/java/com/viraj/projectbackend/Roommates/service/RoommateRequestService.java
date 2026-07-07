package com.viraj.projectbackend.Roommates.service;


import com.viraj.projectbackend.Roommates.model.RequestStatus;
import com.viraj.projectbackend.Roommates.model.RoommateRequest;
import com.viraj.projectbackend.Roommates.repo.RoommateRequest.RoommateRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoommateRequestService {

    @Autowired
    private RoommateRequestRepo roommateRequestRepo;

    // Get all requests
    public List<RoommateRequest> getAllRequests() {
        return roommateRequestRepo.findAll();
    }

    // Get request by id
    public RoommateRequest getRequestById(Long id) {
        return roommateRequestRepo.findById(id).orElse(null);
    }

    // Send request
    public RoommateRequest sendRequest(RoommateRequest request) {
        request.setStatus(RequestStatus.PENDING);
        return roommateRequestRepo.save(request);
    }

    // Update request status
    public RoommateRequest updateRequestStatus(Long id, RequestStatus status) {

        RoommateRequest request =
                roommateRequestRepo.findById(id).orElse(null);

        if (request != null) {
            request.setStatus(status);
            return roommateRequestRepo.save(request);
        }

        return null;
    }

    // Delete request
    public void deleteRequest(Long id) {
        roommateRequestRepo.deleteById(id);
    }
}
