package com.example.coreCRM.service.impl;

import com.example.coreCRM.constants.ValidationConstants;
import com.example.coreCRM.dto.request.CreateTaskRequest;
import com.example.coreCRM.dto.response.TaskResponse;
import com.example.coreCRM.entity.TaskEntity;
import com.example.coreCRM.mapper.TaskMapper;
import com.example.coreCRM.repository.TaskRepository;
import com.example.coreCRM.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        TaskEntity entity = taskMapper.toEntity(request);
        entity.setId(UUID.randomUUID());
        return taskMapper.toResponse(taskRepository.save(entity));
    }

    @Override
    public TaskResponse getTaskById(UUID id) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, ValidationConstants.TASK_NOT_FOUND));
        return taskMapper.toResponse(entity);
    }

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse updateTask(UUID id, CreateTaskRequest request) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, ValidationConstants.TASK_NOT_FOUND));
        taskMapper.toEntity(request, entity);
        return taskMapper.toResponse(taskRepository.save(entity));
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }
}