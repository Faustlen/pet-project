package com.example.coreCRM.controller;

import com.example.coreCRM.dto.request.AssignTaskRequest;
import com.example.coreCRM.dto.request.CreateTaskRequest;
import com.example.coreCRM.dto.response.TaskResponse;
import com.example.coreCRM.mapper.TaskMapper;
import com.example.coreCRM.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping
    public TaskResponse createTask(@RequestBody @Valid CreateTaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable UUID id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks(
            @RequestParam(required = false) UUID userId,
            @RequestParam(required = false) UUID buildingId
    ) {
        if (userId == null && buildingId == null) {
            return taskService.getAllTasks();
        } else {
            return taskService.findAllFiltered(userId, buildingId);
        }
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable UUID id, @RequestBody @Valid CreateTaskRequest request) {
        return taskService.updateTask(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
    }

    @PostMapping("/{taskId}/assign")
    public ResponseEntity<TaskResponse> assignTask(@PathVariable UUID taskId, @Valid @RequestBody AssignTaskRequest request) {
        return ResponseEntity.ok(taskService.assignToUser(taskId, request.getUserId()));
    }

}
