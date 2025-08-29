package com.example.coreCRM.service;

import com.example.coreCRM.dto.request.CreateTaskRequest;
import com.example.coreCRM.dto.response.TaskResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse createTask(CreateTaskRequest request);
    TaskResponse getTaskById(UUID id);
    List<TaskResponse> getAllTasks();
    TaskResponse updateTask(UUID id, CreateTaskRequest request);
    void deleteTask(UUID id);
}