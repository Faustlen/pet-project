package com.example.coreCRM.mapper;

import com.example.coreCRM.dto.request.CreateTaskRequest;
import com.example.coreCRM.dto.response.TaskResponse;
import com.example.coreCRM.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "building_id", ignore = true)
    @Mapping(target = "version", ignore = true)
    TaskEntity toEntity(CreateTaskRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "building_id", ignore = true)
    @Mapping(target = "version", ignore = true)
    TaskEntity toEntity(CreateTaskRequest request, @MappingTarget TaskEntity task);

    @Mapping(source = "user_id.id", target = "userId")
    @Mapping(source = "building_id.id", target = "buildingId")
    TaskResponse toResponse(TaskEntity entity);
}