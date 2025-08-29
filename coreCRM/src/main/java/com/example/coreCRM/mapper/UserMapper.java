package com.example.coreCRM.mapper;

import com.example.coreCRM.dto.request.CreateUserRequest;
import com.example.coreCRM.dto.response.UserResponse;
import com.example.coreCRM.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(CreateUserRequest dto);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(CreateUserRequest dto, @MappingTarget UserEntity user);

    UserResponse toResponse(UserEntity entity);
}
