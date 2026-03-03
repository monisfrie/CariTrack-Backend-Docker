package com.caritrack.caritrack_api.association.domain;


import java.util.List;
import java.util.Optional;

public interface AssociationRepository {

    Association save(Association association);

    Optional<Association> findById(Long id);

    List<Association> findAll();

    void deleteById(Long id);

    List<Association> findByCategory(String category);

}

