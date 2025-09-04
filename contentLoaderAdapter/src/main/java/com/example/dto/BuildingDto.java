package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {
    private String cadastre;
    private String type;
    private String square;
    private String price;
    private String source;
}