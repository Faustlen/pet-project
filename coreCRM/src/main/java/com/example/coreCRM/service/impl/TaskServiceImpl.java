package com.example.coreCRM.service.impl;

import com.example.coreCRM.constants.ValidationConstants;
import com.example.coreCRM.dto.request.CreateTaskRequest;
import com.example.coreCRM.dto.response.TaskResponse;
import com.example.coreCRM.entity.BuildingEntity;
import com.example.coreCRM.entity.TaskEntity;
import com.example.coreCRM.mapper.TaskMapper;
import com.example.coreCRM.repository.BuildingRepository;
import com.example.coreCRM.repository.TaskRepository;
import com.example.coreCRM.repository.UserRepository;
import com.example.coreCRM.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        TaskEntity entity = taskMapper.toEntity(request);
        allowUserAndBuilding(request, entity);
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
    public List<TaskResponse> findAllFiltered(UUID userId, UUID buildingId) {
        return taskRepository.findAllFiltered(userId, buildingId).stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse updateTask(UUID id, CreateTaskRequest request) {
        TaskEntity entity = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, ValidationConstants.TASK_NOT_FOUND));
        taskMapper.toEntity(request, entity);
        allowUserAndBuilding(request, entity);
        return taskMapper.toResponse(taskRepository.save(entity));
    }

    private void allowUserAndBuilding(CreateTaskRequest request, TaskEntity entity) {
        if (request.getUserId() != null) {
            entity.setUser_id(userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException(ValidationConstants.USER_NOT_FOUND)));
        }
        if (request.getBuildingId() != null) {
            entity.setBuilding_id(buildingRepository.findById(request.getBuildingId())
                    .orElseThrow(() -> new EntityNotFoundException(ValidationConstants.BUILDING_NOT_FOUND)));
        }
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional
    public TaskResponse assignToUser(UUID taskId, UUID userId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException(ValidationConstants.TASK_NOT_FOUND));

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ValidationConstants.USER_NOT_FOUND));

        task.setUser_id(user);

        try {
            TaskEntity updated = taskRepository.saveAndFlush(task);
            return taskMapper.toResponse(updated);
        } catch (OptimisticLockException e) {
            throw new ConcurrentModificationException(ValidationConstants.TASK_MODIFIED);
        }
    }

    @Override
    public TaskResponse createAutoContactTask(BuildingEntity building) {
        TaskEntity task = new TaskEntity();
        task.setId(UUID.randomUUID());
        task.setTitle("Контакт с владельцем");
        task.setBuilding_id(building);
        task.setUser_id(null);
        return taskMapper.toResponse(taskRepository.save(task));
    }
}