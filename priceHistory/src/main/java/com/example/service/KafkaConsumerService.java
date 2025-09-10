package com.example.service;

import com.example.dto.BuildingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final PriceHistoryService priceHistoryService;

    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenBatch(List<BuildingDto> batch) {
        priceHistoryService.saveBatch(batch);
    }
}
