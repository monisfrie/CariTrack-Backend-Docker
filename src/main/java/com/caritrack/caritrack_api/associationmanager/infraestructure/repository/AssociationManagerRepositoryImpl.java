package com.caritrack.caritrack_api.associationmanager.infraestructure.repository;


import com.caritrack.caritrack_api.associationmanager.domain.AssociationManager;
import com.caritrack.caritrack_api.associationmanager.domain.AssociationManagerRepository;
import com.caritrack.caritrack_api.associationmanager.infraestructure.mapper.AssociationManagerRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AssociationManagerRepositoryImpl implements AssociationManagerRepository {

    private final AssociationManagerJpaRepository jpaRepository;
    private final AssociationManagerRepositoryMapper mapper;

    @Override
    public AssociationManager save(AssociationManager associationManager) {
        return mapper.toDomain(
                jpaRepository.save(mapper.toJpa(associationManager))
        );
    }

    @Override
    public Optional<AssociationManager> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<AssociationManager> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<AssociationManager> findByAssociationId(Long associationId) {
        return jpaRepository.findByAssociationId(associationId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<AssociationManager> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
