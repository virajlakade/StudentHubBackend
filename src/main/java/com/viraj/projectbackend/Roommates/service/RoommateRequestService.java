package com.viraj.projectbackend.Roommates.service;

import com.viraj.projectbackend.Roommates.model.RoommateRequest.RequestStatus;
import com.viraj.projectbackend.Roommates.model.RoommateRequest.RoommateRequest;
import com.viraj.projectbackend.Roommates.repo.RoommateRequestRepo;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoommateRequestService {

    @Autowired
    private RoommateRequestRepo roommateRequestRepo;

    @Autowired
    private RoommateEmailService roommateEmailService;

    @Autowired
    private UserRepository userRepository;

    // Get all requests
    public List<RoommateRequest> getAllRequests() {
        return roommateRequestRepo.findAll();
    }

    // Get request by id
    public RoommateRequest getRequestById(Long id) {
        return roommateRequestRepo.findById(id).orElse(null);
    }

    // Send connection request
    public RoommateRequest sendRequest(RoommateRequest request) {

        request.setStatus(RequestStatus.PENDING);

        RoommateRequest savedRequest = roommateRequestRepo.save(request);

        try {

            User sender = userRepository.findById(
                    savedRequest.getSender().getId()
            ).orElseThrow(() ->
                    new RuntimeException("Sender not found"));

            User receiver = userRepository.findById(
                    savedRequest.getReceiver().getId()
            ).orElseThrow(() ->
                    new RuntimeException("Receiver not found"));

            roommateEmailService.sendConnectionRequestMail(
                    receiver.getEmail(),
                    receiver.getFullName(),
                    sender.getFullName(),
                    sender.getEmail(),
                    savedRequest.getMessage()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedRequest;
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