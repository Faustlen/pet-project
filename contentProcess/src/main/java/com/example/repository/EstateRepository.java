package com.example.repository;

import com.example.entity.Estate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstateRepository extends JpaRepository<Estate, Long> {

    @Modifying
    @Query(value = """
        INSERT INTO estate (cadastre, source, type, square, price)
        VALUES (:cadastre, :source, :type, :square, :price)
        ON CONFLICT (cadastre, source)
        DO UPDATE SET
            type = EXCLUDED.type,
            square = EXCLUDED.square,
            price = EXCLUDED.price
        """, nativeQuery = true)
    void upsertEstate(String cadastre, String source, String type, String square, String price);
}