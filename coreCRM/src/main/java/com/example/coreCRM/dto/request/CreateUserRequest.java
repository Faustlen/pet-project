package com.example.coreCRM.dto.request;

import com.example.coreCRM.constants.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = ValidationConstants.USER_NAME_HAS_TO_BE_PRESENT)
    @Size(min = 3, max = 255, message = ValidationConstants.USERNAME_SIZE_NOT_VALID)
    private String name;

}
