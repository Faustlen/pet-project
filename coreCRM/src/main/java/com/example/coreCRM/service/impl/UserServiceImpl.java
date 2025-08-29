package com.example.coreCRM.service.impl;

import com.example.coreCRM.constants.ValidationConstants;
import com.example.coreCRM.dto.request.CreateUserRequest;
import com.example.coreCRM.dto.response.UserResponse;
import com.example.coreCRM.entity.UserEntity;
import com.example.coreCRM.mapper.UserMapper;
import com.example.coreCRM.repository.UserRepository;
import com.example.coreCRM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        UserEntity entity = userMapper.toEntity(request);
        entity.setId(UUID.randomUUID());
        return userMapper.toResponse(userRepository.save(entity));
    }

    @Override
    public UserResponse getUserById(UUID id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, ValidationConstants.USER_NOT_FOUND));
        return userMapper.toResponse(entity);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(UUID id, CreateUserRequest request) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, ValidationConstants.USER_NOT_FOUND));
        userMapper.toEntity(request, entity);
        return userMapper.toResponse(userRepository.save(entity));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}