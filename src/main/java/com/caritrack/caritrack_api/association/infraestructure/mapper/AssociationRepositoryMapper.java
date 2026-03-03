package com.caritrack.caritrack_api.association.infraestructure.mapper;


import com.caritrack.caritrack_api.association.domain.Association;
import com.caritrack.caritrack_api.association.infraestructure.repository.AssociationJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssociationRepositoryMapper {

    AssociationJpa toJpa(Association association);

    Association toDomain(AssociationJpa associationJpa);
}

