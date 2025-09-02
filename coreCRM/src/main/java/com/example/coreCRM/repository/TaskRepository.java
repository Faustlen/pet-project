package com.example.coreCRM.repository;

import com.example.coreCRM.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {

    @Query("""
           SELECT t FROM TaskEntity t
           WHERE (:userId IS NULL OR t.user_id.id = :userId)
             AND (:buildingId IS NULL OR t.building_id.id = :buildingId)
           """)
    List<TaskEntity> findAllFiltered(@Param("userId") UUID userId,
                                     @Param("buildingId") UUID buildingId);
}