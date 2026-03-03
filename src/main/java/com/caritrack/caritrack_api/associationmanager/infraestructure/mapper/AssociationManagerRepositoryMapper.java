package com.caritrack.caritrack_api.associationmanager.infraestructure.mapper;

import com.caritrack.caritrack_api.associationmanager.domain.AssociationManager;
import com.caritrack.caritrack_api.associationmanager.infraestructure.repository.AssociationManagerJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssociationManagerRepositoryMapper {

    AssociationManagerJpa toJpa(AssociationManager domain);

    AssociationManager toDomain(AssociationManagerJpa jpa);
}
