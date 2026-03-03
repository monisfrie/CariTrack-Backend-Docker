package com.caritrack.caritrack_api.request.infraestructure.repository;

import com.caritrack.caritrack_api.request.domain.Request;
import com.caritrack.caritrack_api.request.domain.RequestRepository;
import com.caritrack.caritrack_api.request.domain.RequestStatus;
import com.caritrack.caritrack_api.request.infraestructure.mapper.RequestRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RequestRepositoryImpl implements RequestRepository {

    private final RequestJpaRepository jpaRepository;
    private final RequestRepositoryMapper mapper;

    @Override
    public Request save(Request request) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(request)));
    }

    @Override
    public Optional<Request> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Request> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Request> findByAssociationId(Long associationId) {
        return jpaRepository.findByAssociationId(associationId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Request> findByStatus(RequestStatus status) {
        return jpaRepository.findByStatus(status).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}