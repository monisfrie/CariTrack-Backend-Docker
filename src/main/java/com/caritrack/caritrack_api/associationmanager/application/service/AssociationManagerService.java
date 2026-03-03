package com.caritrack.caritrack_api.associationmanager.application.service;



import com.caritrack.caritrack_api.associationmanager.infraestructure.controller.dtos.*;

import java.util.List;
import java.util.UUID;

public interface AssociationManagerService {

    AssociationManagerResponseDto create(AssociationManagerRequestDto request);

    List<AssociationManagerResponseDto> getAll();

    List<AssociationManagerResponseDto> getByAssociation(Long associationId);

    List<AssociationManagerResponseDto> getByUser(UUID userId);

    void delete(Long id);
}
