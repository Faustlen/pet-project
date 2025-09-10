package com.example.scheduler;

import com.example.dto.BuildingDto;
import com.example.service.KafkaProducerService;
import com.example.service.XlsxService;
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

    private int cianIndex = 0;
    private int rosreestrIndex = 0;
    private int domclickIndex = 0;
    private static final int BATCH_SIZE = 10;

    @Scheduled(fixedDelay = 10000)
    public void sendCianBatch() throws IOException {
        processFile("contentLoaderAdapter/data/cian/offers.xlsx", "Cian", cianIndex);
        cianIndex += BATCH_SIZE;
    }

    @Scheduled(fixedDelay = 10000)
    public void sendRosreestrBatch() throws IOException {
        processFile("contentLoaderAdapter/data/rosreestr/offers.xlsx", "Rosreestr", rosreestrIndex);
        rosreestrIndex += BATCH_SIZE;
    }

    @Scheduled(fixedDelay = 10000)
    public void sendDomklikBatch() throws IOException {
        processFile("contentLoaderAdapter/data/domclick/offers.xlsx", "Domklik", domclickIndex);
        domclickIndex += BATCH_SIZE;
    }

    private void processFile(String path, String source, int index) throws IOException {

        File file = new File(path);
        List<BuildingDto> batch = xlsxService.processFile(file, source, index, BATCH_SIZE);

        if (!batch.isEmpty()) {
            producer.publish(batch);
        }
    }
}