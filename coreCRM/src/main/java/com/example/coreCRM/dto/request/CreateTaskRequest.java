package com.example.coreCRM.dto.request;

import com.example.coreCRM.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateTaskRequest {

    @NotBlank(message = ValidationConstants.TASK_TITLE_REQUIRED)
    @Size(max = 255, message = ValidationConstants.TASK_TITLE_SIZE_NOT_VALID)
    private String title;

    @NotNull(message = ValidationConstants.USER_ID_REQUIRED)
    private UUID userId;

    @NotNull(message = ValidationConstants.BUILDING_ID_REQUIRED)
    private UUID buildingId;
}
