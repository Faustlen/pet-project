package com.example.repository;

import com.example.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstateRepository extends JpaRepository<Estate, Long> {
    Optional<Estate> findByCadastreAndSource(String cadastre, String source);
}