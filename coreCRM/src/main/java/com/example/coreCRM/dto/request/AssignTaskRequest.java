package com.example.coreCRM.dto.request;

import com.example.coreCRM.constants.ValidationConstants;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class AssignTaskRequest {

    @NotNull(message = ValidationConstants.USER_ID_REQUIRED)
    private UUID userId;
}