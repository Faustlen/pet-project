package com.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationSenderService sender;

    @Retryable(
            value = { Exception.class },
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000)
    )
    public void send(String type,String text) throws Exception {
        if ("SMS".equalsIgnoreCase(type)) {
            sender.sendSms("phone", text);
        } else if ("EMAIL".equalsIgnoreCase(type)) {
            sender.sendEmail("harbinger8050@gmail.com", text);
        } else {
            throw new IllegalArgumentException("");
        }
        log.info("Уведомление отправлено: {}", text);
    }
}
