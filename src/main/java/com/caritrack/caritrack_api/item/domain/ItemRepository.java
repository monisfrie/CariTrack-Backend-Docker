package com.caritrack.caritrack_api.item.domain;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);

    Optional<Item> findById(Long id);

    Optional<Item> findByApiId(String apiId);

    List<Item> findAll();

    List<Item> findByCategory(String category);

    void deleteById(Long id);
}
