package com.caritrack.caritrack_api.item.application.service;

import com.caritrack.caritrack_api.item.application.mapper.ItemMapperService;
import com.caritrack.caritrack_api.item.domain.Item;
import com.caritrack.caritrack_api.item.domain.ItemRepository;
import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemRequestDto;
import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemResponseDto;
import com.caritrack.caritrack_api.item.utils.exceptions.ItemNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemMapperService mapper;
    private final MessageService messageService;

    @Value("${foodapi.source}")
    private String apiSource;

    @Override
    public ItemResponseDto createItem(ItemRequestDto request) {

        Item item = mapper.toDomain(request);
        item.setApiSource(apiSource);

        Item saved = repository.save(item);
        return mapper.toResponseDto(saved);
    }

    @Override
    public List<ItemResponseDto> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public ItemResponseDto getById(Long id) {
        Item item = repository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(messageService.getMessage("error.item.not.found", id))
        );
        return mapper.toResponseDto(item);
    }

    @Override
    public ItemResponseDto getByApiId(String apiId) {
        Item item = repository.findByApiId(apiId).orElseThrow(() ->
                new ItemNotFoundException(messageService.getMessage(
                        "error.api.item.not.found", apiId))
        );
        return mapper.toResponseDto(item);
    }

    @Override
    public List<ItemResponseDto> getByCategory(String category) {
        return repository.findByCategory(category).stream().map(mapper::toResponseDto).toList();
    }

    @Override
    public void delete(Long id) {
        existingItem(id);
        repository.deleteById(id);
    }

    private void existingItem(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(messageService.getMessage("error.item.not.found", id)));
    }
}