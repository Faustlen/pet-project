package com.example.coreCRM.service;

import com.example.coreCRM.dto.request.CreateBuildingRequest;
import com.example.coreCRM.dto.response.BuildingResponse;

import java.util.List;
import java.util.UUID;

public interface BuildingService {
    BuildingResponse createBuilding(CreateBuildingRequest request);
    BuildingResponse getBuildingById(UUID id);
    List<BuildingResponse> getAllBuildings();
    BuildingResponse updateBuilding(UUID id, CreateBuildingRequest request);
    void deleteBuilding(UUID id);
}