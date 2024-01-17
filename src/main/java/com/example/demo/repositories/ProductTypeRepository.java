package com.example.demo.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, UUID> {
    Optional<ProductType> findByName(String name);
}
