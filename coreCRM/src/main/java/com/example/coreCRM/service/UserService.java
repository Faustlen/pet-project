package com.example.coreCRM.service;

import com.example.coreCRM.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserEntity> getAllUsers();
    Optional<UserEntity> getUserById(UUID id);
    UserEntity createUser(UserEntity user);
    UserEntity updateUser(UUID id, UserEntity user);
    void deleteUser(UUID id);
}
