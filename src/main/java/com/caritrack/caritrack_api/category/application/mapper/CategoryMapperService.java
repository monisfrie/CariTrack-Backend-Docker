package com.caritrack.caritrack_api.category.application.mapper;

import com.caritrack.caritrack_api.category.domain.Category;
import com.caritrack.caritrack_api.category.infraestructure.controller.dtos.CategoryRequestDto;
import com.caritrack.caritrack_api.category.infraestructure.controller.dtos.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapperService {

    Category toDomain(CategoryRequestDto dto);

    CategoryResponseDto toResponseDto(Category category);
}