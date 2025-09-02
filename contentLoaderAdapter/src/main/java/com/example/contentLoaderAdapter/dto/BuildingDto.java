package com.example.contentLoaderAdapter.dto;

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