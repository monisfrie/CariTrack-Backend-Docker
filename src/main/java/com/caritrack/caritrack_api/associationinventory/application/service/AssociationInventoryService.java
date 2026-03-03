package com.caritrack.caritrack_api.associationinventory.application.service;

import com.caritrack.caritrack_api.associationinventory.infraestructure.controller.dtos.*;

import java.util.List;

public interface AssociationInventoryService {

    AssociationInventoryResponseDto create(AssociationInventoryRequestDto request);

    List<AssociationInventoryResponseDto> getAll();

    AssociationInventoryResponseDto getById(Long id);

    List<AssociationInventoryResponseDto> getByAssociation(Long associationId);

    List<AssociationInventoryResponseDto> getByItem(Long itemId);

    AssociationInventoryResponseDto update(Long id, AssociationInventoryRequestDto request);

    AssociationInventoryResponseDto upsert(AssociationInventoryRequestDto request);

    void delete(Long id);

    void deleteByAssociationAndItem(Long associationId, Long itemId);
}