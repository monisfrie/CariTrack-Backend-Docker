package com.caritrack.caritrack_api.associationmanager.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssociationManagerRepository {

    AssociationManager save(AssociationManager associationManager);

    Optional<AssociationManager> findById(Long id);

    List<AssociationManager> findAll();

    List<AssociationManager> findByAssociationId(Long associationId);

    List<AssociationManager> findByUserId(UUID userId);

    void deleteById(Long id);
}
