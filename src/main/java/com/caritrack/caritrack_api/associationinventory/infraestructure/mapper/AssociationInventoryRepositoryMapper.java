package com.caritrack.caritrack_api.associationinventory.infraestructure.mapper;

import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventory;
import com.caritrack.caritrack_api.associationinventory.infraestructure.repository.AssociationInventoryJpa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssociationInventoryRepositoryMapper {

    AssociationInventoryJpa toJpa(AssociationInventory inventory);

    AssociationInventory toDomain(AssociationInventoryJpa inventoryJpa);
}