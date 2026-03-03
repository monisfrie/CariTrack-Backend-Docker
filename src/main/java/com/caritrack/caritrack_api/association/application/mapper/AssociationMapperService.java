package com.caritrack.caritrack_api.association.application.mapper;

import com.caritrack.caritrack_api.association.domain.Association;
import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationRequestDto;
import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AssociationMapperService {

    AssociationResponseDto toResponseDto(Association association);

    Association toDomain(AssociationRequestDto requestDto);
}

