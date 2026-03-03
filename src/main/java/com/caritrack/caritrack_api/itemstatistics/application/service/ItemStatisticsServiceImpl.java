package com.caritrack.caritrack_api.itemstatistics.application.service;

import com.caritrack.caritrack_api.association.domain.AssociationRepository;
import com.caritrack.caritrack_api.association.utils.exceptions.AssociationNotFoundException;
import com.caritrack.caritrack_api.item.domain.ItemRepository;
import com.caritrack.caritrack_api.item.utils.exceptions.ItemNotFoundException;
import com.caritrack.caritrack_api.itemstatistics.application.mapper.ItemStatisticsMapperService;
import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatistics;
import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatisticsRepository;
import com.caritrack.caritrack_api.itemstatistics.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.itemstatistics.utils.exceptions.ItemStatisticsNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemStatisticsServiceImpl implements ItemStatisticsService {

    private final ItemStatisticsRepository repository;
    private final ItemStatisticsMapperService mapper;
    private final MessageService messageService;

    private final AssociationRepository associationRepository;
    private final ItemRepository itemRepository;

    @Override
    public ItemStatisticsResponseDto upsert(ItemStatisticsUpsertDto request) {

        existingAssociation(request.getAssociationId());
        existingItem(request.getItemId());

        ItemStatistics stats = repository.findByAssociationIdAndItemId(
                        request.getAssociationId(),
                        request.getItemId()
                )
                .orElse(ItemStatistics.builder()
                        .associationId(request.getAssociationId())
                        .itemId(request.getItemId())
                        .totalReceived(0)
                        .totalDelivered(0)
                        .totalRequested(0)
                        .build());

        // Si el dto trae null, no sobreescribimos (por comodidad)
        if (request.getTotalReceived() != null) stats.setTotalReceived(request.getTotalReceived());
        if (request.getTotalDelivered() != null) stats.setTotalDelivered(request.getTotalDelivered());
        if (request.getTotalRequested() != null) stats.setTotalRequested(request.getTotalRequested());

        stats.setLastCalculated(Instant.now());

        return mapper.toResponseDto(repository.save(stats));
    }

    @Override
    public List<ItemStatisticsResponseDto> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public ItemStatisticsResponseDto getById(Long id) {
        ItemStatistics stats = repository.findById(id).orElseThrow(() ->
                new ItemStatisticsNotFoundException(
                        messageService.getMessage("error.stats.not.found", id)
                )
        );
        return mapper.toResponseDto(stats);
    }

    @Override
    public List<ItemStatisticsResponseDto> getByAssociation(Long associationId) {
        existingAssociation(associationId);
        return repository.findByAssociationId(associationId).stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public List<ItemStatisticsResponseDto> getByItem(Long itemId) {
        existingItem(itemId);
        return repository.findByItemId(itemId).stream().map(mapper::toResponseDto).toList();
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