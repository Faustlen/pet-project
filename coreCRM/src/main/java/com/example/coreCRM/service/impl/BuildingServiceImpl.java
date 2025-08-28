package com.example.coreCRM.service.impl;

import com.example.coreCRM.entity.BuildingEntity;
import com.example.coreCRM.repository.BuildingRepository;
import com.example.coreCRM.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Override
    public List<BuildingEntity> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public Optional<BuildingEntity> getBuildingById(UUID id) {
        return buildingRepository.findById(id);
    }

    @Override
    public BuildingEntity createBuilding(BuildingEntity building) {
        building.setId(UUID.randomUUID());
        return buildingRepository.save(building);
    }

    @Override
    public BuildingEntity updateBuilding(UUID id, BuildingEntity building) {
        return buildingRepository.findById(id)
                .map(existing -> {
                    existing.setAddress(building.getAddress());
                    return buildingRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Building not found"));
    }

    @Override
    public void deleteBuilding(UUID id) {
        buildingRepository.deleteById(id);
    }
}