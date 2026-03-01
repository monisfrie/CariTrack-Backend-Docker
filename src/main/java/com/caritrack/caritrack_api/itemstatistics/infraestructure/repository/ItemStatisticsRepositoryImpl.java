package com.caritrack.caritrack_api.itemstatistics.infraestructure.repository;

import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatistics;
import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatisticsRepository;
import com.caritrack.caritrack_api.itemstatistics.infraestructure.mapper.ItemStatisticsRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemStatisticsRepositoryImpl implements ItemStatisticsRepository {

    private final ItemStatisticsJpaRepository jpaRepository;
    private final ItemStatisticsRepositoryMapper mapper;

    @Override
    public ItemStatistics save(ItemStatistics stats) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(stats)));
    }

    @Override
    public Optional<ItemStatistics> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<ItemStatistics> findByAssociationIdAndItemId(Long associationId, Long itemId) {
        return jpaRepository.findByAssociationIdAndItemId(associationId, itemId).map(mapper::toDomain);
    }

    @Override
    public List<ItemStatistics> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<ItemStatistics> findByAssociationId(Long associationId) {
        return jpaRepository.findByAssociationId(associationId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<ItemStatistics> findByItemId(Long itemId) {
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