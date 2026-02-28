package com.caritrack.caritrack_api.category.application.service;

import com.caritrack.caritrack_api.category.application.mapper.CategoryMapperService;
import com.caritrack.caritrack_api.category.domain.Category;
import com.caritrack.caritrack_api.category.domain.CategoryRepository;
import com.caritrack.caritrack_api.category.infraestructure.controller.dtos.CategoryRequestDto;
import com.caritrack.caritrack_api.category.infraestructure.controller.dtos.CategoryResponseDto;
import com.caritrack.caritrack_api.category.utils.exceptions.CategoryNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapperService mapper;
    private final MessageService messageService;

    @Override
    public CategoryResponseDto create(CategoryRequestDto request) {
        Category saved = repository.save(mapper.toDomain(request));
        return mapper.toResponseDto(saved);
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = repository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException(messageService.getMessage(
                        "error.category.not.found",
                        id
                ))
        );
        return mapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto getByName(String name) {
        Category category = repository.findByName(name).orElseThrow(() ->
                new CategoryNotFoundException(messageService.getMessage(
                        "error.category.not.found.name",
                        name
                ))
        );
        return mapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto request) {
        Category existing = repository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException(messageService.getMessage(
                        "error.category.not.found",
                        id
                ))
        );

        existing.setName(request.getName());
        Category updated = repository.save(existing);

        return mapper.toResponseDto(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}