package com.viraj.projectbackend.Roommates.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RoommateEmailService {

    private final JavaMailSender mailSender;

    public RoommateEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConnectionRequestMail(
            String ownerEmail,
            String ownerName,
            String senderName,
            String senderEmail,
            String message
    ) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(ownerEmail);
        mail.setSubject("New Roommate Connection Request");

        mail.setText(
                "Hello " + ownerName + ",\n\n" +
                        senderName + " wants to connect with you regarding your roommate post.\n\n" +
                        "Student Name: " + senderName + "\n" +
                        "Email: " + senderEmail + "\n\n" +
                        "Message:\n" +
                        message + "\n\n" +
                        "Please login to StudentHub to respond."
        );

        mailSender.send(mail);
    }
}