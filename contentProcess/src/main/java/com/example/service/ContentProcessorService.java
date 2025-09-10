package com.example.service;

import com.example.dto.BuildingDto;
import com.example.entity.Estate;
import com.example.repository.EstateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentProcessorService {

    private final EstateRepository estateRepository;

    @Transactional
    public void processBatch(List<BuildingDto> updates) {
        for (BuildingDto dto : updates) {
            estateRepository.upsertEstate(
                    dto.getCadastre(),
                    dto.getSource(),
                    dto.getType(),
                    dto.getSquare(),
                    dto.getPrice()
            );
        }
    }
}