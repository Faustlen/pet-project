package com.example.coreCRM.service.impl;

import com.example.coreCRM.constants.ValidationConstants;
import com.example.coreCRM.dto.request.CreateBuildingRequest;
import com.example.coreCRM.dto.response.BuildingResponse;
import com.example.coreCRM.entity.BuildingEntity;
import com.example.coreCRM.mapper.BuildingMapper;
import com.example.coreCRM.repository.BuildingRepository;
import com.example.coreCRM.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    @Override
    public BuildingResponse createBuilding(CreateBuildingRequest request) {
        BuildingEntity entity = buildingMapper.toEntity(request);
        entity.setId(UUID.randomUUID());
        return buildingMapper.toResponse(buildingRepository.save(entity));
    }

    @Override
    public BuildingResponse getBuildingById(UUID id) {
        BuildingEntity entity = buildingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, ValidationConstants.BUILDING_NOT_FOUND));
        return buildingMapper.toResponse(entity);
    }

    @Override
    public List<BuildingResponse> getAllBuildings() {
        return buildingRepository.findAll().stream()
                .map(buildingMapper::toResponse)
                .toList();
    }

    @Override
    public BuildingResponse updateBuilding(UUID id, CreateBuildingRequest request) {
        BuildingEntity entity = buildingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, ValidationConstants.BUILDING_NOT_FOUND));
        buildingMapper.toEntity(request, entity);
        return buildingMapper.toResponse(buildingRepository.save(entity));
    }

    @Override
    public void deleteBuilding(UUID id) {
        buildingRepository.deleteById(id);
    }

    @Override
    public List<BuildingEntity> findBuildingsWithoutRecentContact(int days) {
        LocalDate date = LocalDate.now().minusDays(days);
        return buildingRepository.findWithoutRecentContact(date);
    }
}