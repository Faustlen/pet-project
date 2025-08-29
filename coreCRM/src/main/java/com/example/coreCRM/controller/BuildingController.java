package com.example.coreCRM.controller;

import com.example.coreCRM.dto.request.CreateBuildingRequest;
import com.example.coreCRM.dto.response.BuildingResponse;
import com.example.coreCRM.service.BuildingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @PostMapping
    public BuildingResponse createBuilding(@RequestBody @Valid CreateBuildingRequest request) {
        return buildingService.createBuilding(request);
    }

    @GetMapping("/{id}")
    public BuildingResponse getBuildingById(@PathVariable UUID id) {
        return buildingService.getBuildingById(id);
    }

    @GetMapping
    public List<BuildingResponse> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @PutMapping("/{id}")
    public BuildingResponse updateBuilding(@PathVariable UUID id, @RequestBody @Valid CreateBuildingRequest request) {
        return buildingService.updateBuilding(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable UUID id) {
        buildingService.deleteBuilding(id);
    }
}
