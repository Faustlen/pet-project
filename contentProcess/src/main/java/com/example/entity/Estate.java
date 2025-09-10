package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        name = "estate",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cadastre", "source"})
        }
)
@Data
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cadastre;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String square;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String source;
}
