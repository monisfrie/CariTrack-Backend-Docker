package com.caritrack.caritrack_api.request.infraestructure.repository;

import com.caritrack.caritrack_api.request.domain.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestJpaRepository extends JpaRepository<RequestJpa, Long> {

    List<RequestJpa> findByAssociationId(Long associationId);

    List<RequestJpa> findByStatus(RequestStatus status);
}