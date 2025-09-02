package com.example.contentLoaderAdapter.scheduler;

import com.example.contentLoaderAdapter.dto.BuildingDto;
import com.example.contentLoaderAdapter.service.KafkaProducerService;
import com.example.contentLoaderAdapter.service.XlsxService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentLoaderScheduler {

    private final XlsxService xlsxService;
    private final KafkaProducerService producer;

    private int currentRowIndex = 0;
    private static final int BATCH_SIZE = 10;

    @Scheduled(fixedDelay = 10000)
    public void sendBatch() throws IOException {
        File file = new File("contentLoaderAdapter/data/cian/offers.xlsx");

        List<BuildingDto> batch = xlsxService.processFile(file, "Cian", currentRowIndex, BATCH_SIZE);

        if (batch.isEmpty()) {
            return;
        }

        producer.publish(batch);
        currentRowIndex += batch.size();
    }
}