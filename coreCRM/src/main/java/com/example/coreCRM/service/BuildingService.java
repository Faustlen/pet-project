package com.example.coreCRM.service;

import com.example.coreCRM.entity.BuildingEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BuildingService {
    List<BuildingEntity> getAllBuildings();
    Optional<BuildingEntity> getBuildingById(UUID id);
    BuildingEntity createBuilding(BuildingEntity building);
    BuildingEntity updateBuilding(UUID id, BuildingEntity building);
    void deleteBuilding(UUID id);
}