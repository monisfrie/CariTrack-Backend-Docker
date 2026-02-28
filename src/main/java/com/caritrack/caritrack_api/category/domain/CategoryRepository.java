package com.caritrack.caritrack_api.category.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String name);

    List<Category> findAll();

    void deleteById(Long id);
}