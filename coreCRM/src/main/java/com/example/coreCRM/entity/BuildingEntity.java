package com.example.coreCRM.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "buildings")
public class BuildingEntity {

    @Id
    private UUID id;

    private String address;

    private LocalDate lastContactDate;
}