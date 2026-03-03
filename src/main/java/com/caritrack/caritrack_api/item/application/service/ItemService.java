package com.caritrack.caritrack_api.item.application.service;

import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemRequestDto;
import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemResponseDto;

import java.util.List;

public interface ItemService {

    ItemResponseDto createItem(ItemRequestDto request);

    List<ItemResponseDto> getAll();

    ItemResponseDto getById(Long id);

    ItemResponseDto getByApiId(String apiId);

    List<ItemResponseDto> getByCategory(String category);

    void delete(Long id);
}