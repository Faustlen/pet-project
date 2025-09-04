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
        List<Estate> estates = new ArrayList<>();
        for (BuildingDto dto : updates) {
            Estate estate = estateRepository
                    .findByCadastreAndSource(dto.getCadastre(), dto.getSource())
                    .orElseGet(Estate::new);

            estate.setCadastre(dto.getCadastre());
            estate.setType(dto.getType());
            estate.setSquare(dto.getSquare());
            estate.setPrice(dto.getPrice());
            estate.setSource(dto.getSource());

            estates.add(estate);
        }
        estateRepository.saveAll(estates);
    }
}