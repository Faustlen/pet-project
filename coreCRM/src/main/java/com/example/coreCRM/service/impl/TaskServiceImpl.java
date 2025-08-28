package com.example.coreCRM.service.impl;

import com.example.coreCRM.entity.TaskEntity;
import com.example.coreCRM.entity.UserEntity;
import com.example.coreCRM.repository.TaskRepository;
import com.example.coreCRM.repository.UserRepository;
import com.example.coreCRM.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public java.util.Optional<TaskEntity> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    @Override
    public TaskEntity createTask(TaskEntity task) {
        task.setId(UUID.randomUUID());
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity updateTask(UUID id, TaskEntity task) {
        return taskRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(task.getTitle());
                    existing.setBuilding_id(task.getBuilding_id());
                    existing.setUser_id(task.getUser_id());
                    return taskRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskEntity assignTaskToUser(UUID taskId, UUID userId) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser_id(user);
        return taskRepository.save(task);
    }
}