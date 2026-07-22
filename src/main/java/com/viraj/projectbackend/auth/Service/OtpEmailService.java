package com.viraj.projectbackend.auth.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpEmailService {

    private final JavaMailSender mailSender;

    public OtpEmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String toEmail, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("StudentHub Password Reset OTP");

        message.setText(
                "Hello,\n\n" +
                        "We received a request to reset your StudentHub password.\n\n" +
                        "Your One-Time Password (OTP) is:\n\n" +
                        otp +
                        "\n\nThis OTP is valid for 5 minutes.\n\n" +
                        "If you did not request a password reset, please ignore this email.\n\n" +
                        "Regards,\n" +
                        "StudentHub Team"
        );

        mailSender.send(message);
    }
}