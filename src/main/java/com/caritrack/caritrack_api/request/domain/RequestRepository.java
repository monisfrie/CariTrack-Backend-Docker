package com.caritrack.caritrack_api.request.domain;

import java.util.List;
import java.util.Optional;

public interface RequestRepository {

    Request save(Request request);

    Optional<Request> findById(Long id);

    List<Request> findAll();

    List<Request> findByAssociationId(Long associationId);

    List<Request> findByStatus(RequestStatus status);

    void deleteById(Long id);
}