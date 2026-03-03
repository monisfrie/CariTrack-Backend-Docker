package com.caritrack.caritrack_api.category.infraestructure.mapper;

import com.caritrack.caritrack_api.category.domain.Category;
import com.caritrack.caritrack_api.category.infraestructure.repository.CategoryJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryRepositoryMapper {

    CategoryJpa toJpa(Category category);

    Category toDomain(CategoryJpa categoryJpa);
}