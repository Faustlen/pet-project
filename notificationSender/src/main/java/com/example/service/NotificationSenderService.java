package com.example.service;

import lombok.extern.slf4j.Slf4j;import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationSenderService {

    public void sendSms(String phone, String message) throws Exception {
        log.info("Отправка SMS на {}: {}", phone, message);
        if (Math.random() < 0.5) throw new Exception("SMS сервис недоступен");
    }

    public void sendEmail(String email, String message) throws Exception {
        log.info("Отправка Email на {}: {}", email, message);
    }
}
