package com.caritrack.caritrack_api.category.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpa, Long> {
    Optional<CategoryJpa> findByName(String name);
}