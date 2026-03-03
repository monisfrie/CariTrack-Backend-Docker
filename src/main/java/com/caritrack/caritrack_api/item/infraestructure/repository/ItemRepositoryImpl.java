package com.caritrack.caritrack_api.item.infraestructure.repository;

import com.caritrack.caritrack_api.item.domain.Item;
import com.caritrack.caritrack_api.item.domain.ItemRepository;
import com.caritrack.caritrack_api.item.infraestructure.mapper.ItemRepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemJpaRepository jpaRepository;
    private final ItemRepositoryMapper mapper;

    @Override
    public Item save(Item item) {
        ItemJpa jpa = mapper.toJpa(item);
        return mapper.toDomain(jpaRepository.save(jpa));
    }

    @Override
    public Optional<Item> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Item> findByApiId(String apiId) {
        return jpaRepository.findByApiId(apiId).map(mapper::toDomain);
    }

    @Override
    public List<Item> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Item> findByCategory(String category) {
        return jpaRepository.findByCategory(category).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}