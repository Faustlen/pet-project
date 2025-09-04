package com.example.service;

import com.example.dto.BuildingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final ContentProcessorService processorService;

    @KafkaListener(
            topics = "${spring.kafka.template.default-topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
        public void listenBatch(List<BuildingDto> updates) {
        processorService.processBatch(updates);
    }
}
