package com.viraj.projectbackend.Attendance.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendLostFoundMail(
            String ownerEmail,
            String itemTitle,
            String senderName,
            String senderEmail,
            String senderPhone,
            String message
    ) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(ownerEmail);

        mail.setSubject("Someone contacted you regarding your Lost & Found item");

        mail.setText(
                "Hello,\n\n" +

                        "Someone has contacted you regarding your Lost & Found post.\n\n" +

                        "Item : " + itemTitle + "\n\n" +

                        "Finder Details\n" +
                        "----------------------------\n" +
                        "Name : " + senderName + "\n" +
                        "Email : " + senderEmail + "\n" +
                        "Phone : " + senderPhone + "\n\n" +

                        "Message\n" +
                        "----------------------------\n" +
                        message +

                        "\n\n----------------------------------\n" +
                        "StudentHub\n" +
                        "Please contact the finder directly."
        );

        mailSender.send(mail);

    }
}
