package com.example.coreCRM.repository;

import com.example.coreCRM.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BuildingRepository extends JpaRepository<BuildingEntity, UUID> {
}