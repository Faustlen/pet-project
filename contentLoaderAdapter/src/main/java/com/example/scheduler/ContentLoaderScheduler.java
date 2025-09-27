package com.example.scheduler;

import com.example.dto.BuildingDto;
import com.example.service.KafkaProducerService;
import com.example.service.XlsxService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentLoaderScheduler {

    private static final Logger log = LoggerFactory.getLogger(ContentLoaderScheduler.class);

    private final XlsxService xlsxService;
    private final KafkaProducerService producer;

    private int cianIndex = 0;
    private int rosreestrIndex = 0;
    private int domclickIndex = 0;
    private static final int BATCH_SIZE = 10;

    private static final String DATA_DIR = System.getenv().getOrDefault("DATA_DIR", "contentLoaderAdapter/data");

    @Scheduled(fixedDelay = 60000)
    public void sendCianBatch() {
        sendBatch("cian/offers.xlsx", "Cian", cianIndex);
        cianIndex += BATCH_SIZE;
    }

    @Scheduled(fixedDelay = 600000)
    public void sendRosreestrBatch() {
        sendBatch("rosreestr/offers.xlsx", "Rosreestr", rosreestrIndex);
        rosreestrIndex += BATCH_SIZE;
    }

    @Scheduled(fixedDelay = 600000)
    public void sendDomklikBatch() {
        sendBatch("domclick/offers.xlsx", "Domklik", domclickIndex);
        domclickIndex += BATCH_SIZE;
    }

    private void sendBatch(String relativePath, String source, int index) {
        File file = new File(DATA_DIR, relativePath);

        if (!file.exists() || !file.isFile()) {
            log.warn("Файл не найден для источника {}: {}", source, file.getAbsolutePath());
            return;
        }

        try {
            List<BuildingDto> batch = xlsxService.processFile(file, source, index, BATCH_SIZE);
            if (!batch.isEmpty()) {
                producer.publish(batch);
                log.info("Отправлено {} записей для {}", batch.size(), source);
            }
        } catch (IOException e) {
            log.error("Ошибка при обработке файла {} для {}", file.getAbsolutePath(), source, e);
        }
    }
}
