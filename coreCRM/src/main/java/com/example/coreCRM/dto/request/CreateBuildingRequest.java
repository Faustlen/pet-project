package com.example.coreCRM.dto.request;

import com.example.coreCRM.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBuildingRequest {

    @NotBlank(message = ValidationConstants.BUILDING_ADDRESS_REQUIRED)
    @Size(max = 255, message = ValidationConstants.BUILDING_ADDRESS_SIZE_NOT_VALID)
    private String address;
}