package com.caritrack.caritrack_api.associationmanager.application.mapper;

import com.caritrack.caritrack_api.associationmanager.domain.AssociationManager;
import com.caritrack.caritrack_api.associationmanager.infraestructure.controller.dtos.AssociationManagerRequestDto;
import com.caritrack.caritrack_api.associationmanager.infraestructure.controller.dtos.AssociationManagerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssociationManagerMapperService {

    AssociationManager toDomain(AssociationManagerRequestDto dto);

    AssociationManagerResponseDto toResponseDto(AssociationManager domain);
}
