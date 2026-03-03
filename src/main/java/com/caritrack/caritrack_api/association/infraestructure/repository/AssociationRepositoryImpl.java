package com.caritrack.caritrack_api.association.infraestructure.repository;

import com.caritrack.caritrack_api.association.domain.Association;
import com.caritrack.caritrack_api.association.domain.AssociationRepository;
import com.caritrack.caritrack_api.association.infraestructure.mapper.AssociationRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AssociationRepositoryImpl implements AssociationRepository {

    private final AssociationJpaRepository jpaRepository;
    private final AssociationRepositoryMapper mapper;

    @Override
    public Association save(Association association) {
        AssociationJpa jpa = mapper.toJpa(association);
        return mapper.toDomain(jpaRepository.save(jpa));
    }

    @Override
    public Optional<Association> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Association> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Association> findByCategory(String category) {
        return jpaRepository.findByCategory(category)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }


}

