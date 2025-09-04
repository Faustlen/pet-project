package com.example.service;

import com.example.dto.BuildingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, BuildingDto> kafkaTemplate;

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public void publish(List<BuildingDto> batch) {
        batch.forEach(dto ->
                kafkaTemplate.send(topic, dto.getCadastre(), dto)
        );
    }
}
