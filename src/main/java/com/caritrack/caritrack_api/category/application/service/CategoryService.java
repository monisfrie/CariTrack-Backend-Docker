package com.caritrack.caritrack_api.category.application.service;

import com.caritrack.caritrack_api.category.infraestructure.controller.dtos.CategoryRequestDto;
import com.caritrack.caritrack_api.category.infraestructure.controller.dtos.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto create(CategoryRequestDto request);

    List<CategoryResponseDto> getAll();

    CategoryResponseDto getById(Long id);

    CategoryResponseDto getByName(String name);

    CategoryResponseDto update(Long id, CategoryRequestDto request);

    void delete(Long id);
}