package com.example.coreCRM.service;


import com.example.coreCRM.dto.request.CreateUserRequest;
import com.example.coreCRM.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse getUserById(UUID id);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UUID id, CreateUserRequest request);
    void deleteUser(UUID id);
}
