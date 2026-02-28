package com.caritrack.caritrack_api.associationmanager.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssociationManagerJpaRepository
        extends JpaRepository<AssociationManagerJpa, Long> {

    List<AssociationManagerJpa> findByAssociationId(Long associationId);

    List<AssociationManagerJpa> findByUserId(UUID userId);
}
