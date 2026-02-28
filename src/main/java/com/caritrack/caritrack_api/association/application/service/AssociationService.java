package com.caritrack.caritrack_api.association.application.service;

import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationRequestDto;
import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationResponseDto;

import java.util.List;

public interface AssociationService {

    AssociationResponseDto createAssociation(AssociationRequestDto request);

    List<AssociationResponseDto> getAllAssociations();

    AssociationResponseDto getById(Long id);

    void deleteAssociation(Long id);

    AssociationResponseDto updateAssociation(Long id, AssociationRequestDto request);

    List<AssociationResponseDto> getByCategory(String category);

    boolean existsById(Long id);

}

