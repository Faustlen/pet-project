package com.example.coreCRM.service;

import com.example.coreCRM.entity.TaskEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<TaskEntity> getAllTasks();
    Optional<TaskEntity> getTaskById(UUID id);
    TaskEntity createTask(TaskEntity task);
    TaskEntity updateTask(UUID id, TaskEntity task);
    void deleteTask(UUID id);
    TaskEntity assignTaskToUser(UUID taskId, UUID userId);
}