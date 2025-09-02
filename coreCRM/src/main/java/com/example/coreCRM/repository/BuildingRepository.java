package com.example.coreCRM.repository;

import com.example.coreCRM.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BuildingRepository extends JpaRepository<BuildingEntity, UUID> {

    @Query("SELECT b FROM BuildingEntity b WHERE b.lastContactDate IS NULL OR b.lastContactDate < :localDate")
    List<BuildingEntity> findWithoutRecentContact(@Param("cutoffDate") LocalDate localDate);
}