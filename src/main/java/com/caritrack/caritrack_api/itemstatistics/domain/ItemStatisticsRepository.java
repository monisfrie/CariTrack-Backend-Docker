package com.caritrack.caritrack_api.itemstatistics.domain;

import java.util.List;
import java.util.Optional;

public interface ItemStatisticsRepository {

    ItemStatistics save(ItemStatistics stats);

    Optional<ItemStatistics> findById(Long id);

    Optional<ItemStatistics> findByAssociationIdAndItemId(Long associationId, Long itemId);

    List<ItemStatistics> findAll();

    List<ItemStatistics> findByAssociationId(Long associationId);

    List<ItemStatistics> findByItemId(Long itemId);

    void deleteById(Long id);

    void deleteByAssociationIdAndItemId(Long associationId, Long itemId);
}