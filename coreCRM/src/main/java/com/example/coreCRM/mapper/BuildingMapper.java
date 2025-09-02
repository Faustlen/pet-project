package com.example.coreCRM.mapper;

import com.example.coreCRM.dto.request.CreateBuildingRequest;
import com.example.coreCRM.dto.response.BuildingResponse;
import com.example.coreCRM.entity.BuildingEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BuildingMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastContactDate", ignore = true)
    BuildingEntity toEntity(CreateBuildingRequest dto);

    @Mapping(target = "id", ignore = true)
    BuildingEntity toEntity(CreateBuildingRequest dto, @MappingTarget BuildingEntity building);

    BuildingResponse toResponse(BuildingEntity entity);
}