package com.caritrack.caritrack_api.associationinventory.infraestructure.repository;

import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventory;
import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventoryRepository;
import com.caritrack.caritrack_api.associationinventory.infraestructure.mapper.AssociationInventoryRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AssociationInventoryRepositoryImpl implements AssociationInventoryRepository {

    private final AssociationInventoryJpaRepository jpaRepository;
    private final AssociationInventoryRepositoryMapper mapper;

    @Override
    public AssociationInventory save(AssociationInventory inventory) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(inventory)));
    }

    @Override
    public Optional<AssociationInventory> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<AssociationInventory> findByAssociationIdAndItemId(Long associationId, Long itemId) {
        return jpaRepository.findByAssociationIdAndItemId(associationId, itemId).map(mapper::toDomain);
    }

    @Override
    public List<AssociationInventory> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<AssociationInventory> findByAssociationId(Long associationId) {
        return jpaRepository.findByAssociationId(associationId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<AssociationInventory> findByItemId(Long itemId) {
        return jpaRepository.findByItemId(itemId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void deleteByAssociationIdAndItemId(Long associationId, Long itemId) {
        jpaRepository.deleteByAssociationIdAndItemId(associationId, itemId);
    }
}