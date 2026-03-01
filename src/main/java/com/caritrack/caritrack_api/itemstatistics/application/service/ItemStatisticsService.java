package com.caritrack.caritrack_api.itemstatistics.application.service;

import com.caritrack.caritrack_api.itemstatistics.infraestructure.controller.dtos.*;

import java.util.List;

public interface ItemStatisticsService {

    ItemStatisticsResponseDto upsert(ItemStatisticsUpsertDto request);

    List<ItemStatisticsResponseDto> getAll();

    ItemStatisticsResponseDto getById(Long id);

    List<ItemStatisticsResponseDto> getByAssociation(Long associationId);

    List<ItemStatisticsResponseDto> getByItem(Long itemId);

    void delete(Long id);

    void deleteByAssociationAndItem(Long associationId, Long itemId);
}