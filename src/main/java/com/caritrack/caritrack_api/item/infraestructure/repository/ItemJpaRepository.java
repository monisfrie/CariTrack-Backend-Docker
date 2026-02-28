package com.caritrack.caritrack_api.item.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemJpaRepository extends JpaRepository<ItemJpa, Long> {

    Optional<ItemJpa> findByApiId(String apiId);

    List<ItemJpa> findByCategory(String category);
}