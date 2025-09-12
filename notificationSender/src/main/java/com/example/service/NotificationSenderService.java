package com.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationSenderService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSms(String phone, String message) throws Exception {
        log.info("Отправка SMS на {}: {}", phone, message);
        if (Math.random() < 0.5) throw new Exception("SMS сервис недоступен");
    }

    public void sendEmail(String email, String message) throws Exception {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(email);
        mailMessage.setSubject("Оповещение");
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}
