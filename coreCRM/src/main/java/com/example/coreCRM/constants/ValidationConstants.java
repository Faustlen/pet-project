package com.example.coreCRM.constants;

public interface ValidationConstants {

    String USER_NAME_HAS_TO_BE_PRESENT = "User name has to be present";
    String USERNAME_SIZE_NOT_VALID = "Username size should be between 3 and 255";
    String USER_ID_REQUIRED = "User ID is required";
    String USER_NOT_FOUND = "User not found";

    String BUILDING_ADDRESS_REQUIRED = "Building address is required";
    String BUILDING_ADDRESS_SIZE_NOT_VALID = "Building address max size should be 255";
    String BUILDING_ID_REQUIRED = "Building ID is required";
    String BUILDING_NOT_FOUND = "Building not found";

    String TASK_TITLE_REQUIRED = "Task title is required";
    String TASK_TITLE_SIZE_NOT_VALID = "Task title max size should be 255";
    String TASK_NOT_FOUND = "Task not found";
    String TASK_MODIFIED = "Task was modified by another user";
}
