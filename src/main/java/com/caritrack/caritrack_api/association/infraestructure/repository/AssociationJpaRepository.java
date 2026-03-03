package com.caritrack.caritrack_api.association.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociationJpaRepository extends JpaRepository<AssociationJpa, Long> {

    List<AssociationJpa> findByCategory(String category);

}

