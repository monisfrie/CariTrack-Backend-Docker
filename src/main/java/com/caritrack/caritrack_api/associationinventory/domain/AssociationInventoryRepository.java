package com.caritrack.caritrack_api.associationinventory.domain;

import java.util.List;
import java.util.Optional;

public interface AssociationInventoryRepository {

    AssociationInventory save(AssociationInventory inventory);

    Optional<AssociationInventory> findById(Long id);

    Optional<AssociationInventory> findByAssociationIdAndItemId(Long associationId, Long itemId);

    List<AssociationInventory> findAll();

    List<AssociationInventory> findByAssociationId(Long associationId);

    List<AssociationInventory> findByItemId(Long itemId);

    void deleteById(Long id);

    void deleteByAssociationIdAndItemId(Long associationId, Long itemId);
}