package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "estate")
@Data
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cadastre;

    @Column(nullable = false)
    private String type;

    private String square;
    private String price;

    @Column(nullable = false)
    private String source;
}
