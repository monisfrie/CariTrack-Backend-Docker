package com.caritrack.caritrack_api.associationinventory.application.mapper;

import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventory;
import com.caritrack.caritrack_api.associationinventory.infraestructure.controller.dtos.AssociationInventoryRequestDto;
import com.caritrack.caritrack_api.associationinventory.infraestructure.controller.dtos.AssociationInventoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssociationInventoryMapperService {

    AssociationInventory toDomain(AssociationInventoryRequestDto dto);

    AssociationInventoryResponseDto toResponseDto(AssociationInventory domain);
}