package com.example.coreCRM.controller;

import com.example.coreCRM.entity.BuildingEntity;
import com.example.coreCRM.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping
    public List<BuildingEntity> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public BuildingEntity getBuildingById(@PathVariable UUID id) {
        return buildingService.getBuildingById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found"));
    }

    @PostMapping
    public BuildingEntity createBuilding(@RequestBody BuildingEntity building) {
        return buildingService.createBuilding(building);
    }

    @PutMapping("/{id}")
    public BuildingEntity updateBuilding(@PathVariable UUID id, @RequestBody BuildingEntity building) {
        return buildingService.updateBuilding(id, building);
    }

    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable UUID id) {
        buildingService.deleteBuilding(id);
    }
}