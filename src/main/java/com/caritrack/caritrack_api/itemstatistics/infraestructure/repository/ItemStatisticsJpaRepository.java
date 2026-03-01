package com.caritrack.caritrack_api.itemstatistics.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemStatisticsJpaRepository extends JpaRepository<ItemStatisticsJpa, Long> {

    Optional<ItemStatisticsJpa> findByAssociationIdAndItemId(Long associationId, Long itemId);

    List<ItemStatisticsJpa> findByAssociationId(Long associationId);

    List<ItemStatisticsJpa> findByItemId(Long itemId);

    void deleteByAssociationIdAndItemId(Long associationId, Long itemId);
}