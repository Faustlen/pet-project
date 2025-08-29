package com.example.coreCRM.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TaskResponse {
    private UUID id;
    private String title;
    private UUID userId;
    private UUID buildingId;
}
