package com.example.service;

import com.example.dto.BuildingDto;
import com.example.entity.PriceHistory;
import com.example.repository.PriceHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceHistoryService {

    private final PriceHistoryRepository repository;

    @Transactional
    public void saveBatch(List<BuildingDto> dtos) {
        List<PriceHistory> historyList = new ArrayList<>();

        for (BuildingDto dto : dtos) {
            PriceHistory ph = new PriceHistory();
            ph.setCadastre(dto.getCadastre());
            ph.setSource(dto.getSource());
            ph.setPrice(dto.getPrice());
            ph.setCreatedAt(LocalDateTime.now());

            historyList.add(ph);
        }

        repository.saveAll(historyList); // батч-сохранение
    }
}
