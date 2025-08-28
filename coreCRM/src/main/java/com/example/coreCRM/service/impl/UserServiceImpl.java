package com.example.coreCRM.service.impl;

import com.example.coreCRM.entity.UserEntity;
import com.example.coreCRM.repository.UserRepository;
import com.example.coreCRM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UUID id, UserEntity user) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setName(user.getName());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}