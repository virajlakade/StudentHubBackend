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

        System.out.println("=========== ROOMMATE EMAIL ===========");
        System.out.println("Owner Email  : " + ownerEmail);
        System.out.println("Owner Name   : " + ownerName);
        System.out.println("Sender Name  : " + senderName);
        System.out.println("Sender Email : " + senderEmail);
        System.out.println("Message      : " + message);
        System.out.println("======================================");

        if (ownerEmail == null || ownerEmail.isBlank()) {
            throw new RuntimeException("Receiver email is null.");
        }

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(ownerEmail);
        mail.setSubject("New Roommate Connection Request");

        mail.setText(
                "Hello " + ownerName + ",\n\n" +
                        senderName + " has sent you a roommate connection request.\n\n" +
                        "Sender Name : " + senderName + "\n" +
                        "Sender Email: " + senderEmail + "\n\n" +
                        "Message:\n" +
                        message + "\n\n" +
                        "Please login to StudentHub to respond."
        );

        mailSender.send(mail);

        System.out.println("Roommate email sent successfully.");
    }
}