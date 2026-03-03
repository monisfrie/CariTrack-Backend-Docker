package com.caritrack.caritrack_api.associationinventory.application.service;

import com.caritrack.caritrack_api.association.domain.AssociationRepository;
import com.caritrack.caritrack_api.association.utils.exceptions.AssociationNotFoundException;
import com.caritrack.caritrack_api.associationinventory.application.mapper.AssociationInventoryMapperService;
import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventory;
import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventoryRepository;
import com.caritrack.caritrack_api.associationinventory.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.associationinventory.utils.exceptions.AssociationInventoryNotFoundException;
import com.caritrack.caritrack_api.item.domain.ItemRepository;
import com.caritrack.caritrack_api.item.utils.exceptions.ItemNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociationInventoryServiceImpl implements AssociationInventoryService {

    private final AssociationInventoryRepository repository;
    private final AssociationInventoryMapperService mapper;
    private final MessageService messageService;

    private final AssociationRepository associationRepository;
    private final ItemRepository itemRepository;

    @Override
    public AssociationInventoryResponseDto create(AssociationInventoryRequestDto request) {

        existingAssociation(request.getAssociationId());
        existingItem(request.getItemId());

        AssociationInventory domain = mapper.toDomain(request);
        domain.setLastUpdated(Instant.now());

        return mapper.toResponseDto(repository.save(domain));
    }

    @Override
    public List<AssociationInventoryResponseDto> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public AssociationInventoryResponseDto getById(Long id) {
        AssociationInventory inv = repository.findById(id).orElseThrow(() ->
                new AssociationInventoryNotFoundException(
                        messageService.getMessage("error.inventory.not.found", id)
                )
        );
        return mapper.toResponseDto(inv);
    }

    @Override
    public List<AssociationInventoryResponseDto> getByAssociation(Long associationId) {
        existingAssociation(associationId);
        return repository.findByAssociationId(associationId).stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public List<AssociationInventoryResponseDto> getByItem(Long itemId) {
        existingItem(itemId);
        return repository.findByItemId(itemId).stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public AssociationInventoryResponseDto update(Long id, AssociationInventoryRequestDto request) {

        existingAssociation(request.getAssociationId());
        existingItem(request.getItemId());

        AssociationInventory existing = repository.findById(id).orElseThrow(() ->
                new AssociationInventoryNotFoundException(
                        messageService.getMessage("error.inventory.not.found", id)
                )
        );

        existing.setAssociationId(request.getAssociationId());
        existing.setItemId(request.getItemId());
        existing.setStockQuantity(request.getStockQuantity());
        existing.setMinRequired(request.getMinRequired());
        existing.setMaxCapacity(request.getMaxCapacity());
        existing.setLastUpdated(Instant.now());

        return mapper.toResponseDto(repository.save(existing));
    }

    @Override
    public AssociationInventoryResponseDto upsert(AssociationInventoryRequestDto request) {

        existingAssociation(request.getAssociationId());
        existingItem(request.getItemId());

        AssociationInventory inv = repository.findByAssociationIdAndItemId(
                        request.getAssociationId(),
                        request.getItemId()
                )
                .orElse(AssociationInventory.builder()
                        .associationId(request.getAssociationId())
                        .itemId(request.getItemId())
                        .stockQuantity(0)
                        .build());

        inv.setStockQuantity(request.getStockQuantity());
        inv.setMinRequired(request.getMinRequired());
        inv.setMaxCapacity(request.getMaxCapacity());
        inv.setLastUpdated(Instant.now());

        return mapper.toResponseDto(repository.save(inv));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByAssociationAndItem(Long associationId, Long itemId) {
        repository.deleteByAssociationIdAndItemId(associationId, itemId);
    }

    private void existingAssociation(Long associationId) {
        associationRepository.findById(associationId)
                .orElseThrow(() -> new AssociationNotFoundException(
                        messageService.getMessage("error.association.not.found", associationId)
                ));
    }

    private void existingItem(Long itemId) {
        itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(
                        messageService.getMessage("error.item.not.found", itemId)
                ));
    }
}