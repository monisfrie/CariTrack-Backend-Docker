package com.caritrack.caritrack_api.associationinventory.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssociationInventoryJpaRepository extends JpaRepository<AssociationInventoryJpa, Long> {

    Optional<AssociationInventoryJpa> findByAssociationIdAndItemId(Long associationId, Long itemId);

    List<AssociationInventoryJpa> findByAssociationId(Long associationId);

    List<AssociationInventoryJpa> findByItemId(Long itemId);

    void deleteByAssociationIdAndItemId(Long associationId, Long itemId);
}