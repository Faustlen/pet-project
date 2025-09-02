package com.example.coreCRM.service;

import com.example.coreCRM.dto.request.CreateTaskRequest;
import com.example.coreCRM.dto.response.TaskResponse;
import com.example.coreCRM.entity.BuildingEntity;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    TaskResponse getTaskById(UUID id);
    List<TaskResponse> getAllTasks();
    List<TaskResponse> findAllFiltered(UUID userId, UUID buildingId);
    TaskResponse updateTask(UUID id, CreateTaskRequest request);
    void deleteTask(UUID id);
    TaskResponse assignToUser(UUID taskId, UUID userId);
    TaskResponse createAutoContactTask(BuildingEntity building);
}