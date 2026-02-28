package com.caritrack.caritrack_api.item.infraestructure.controller;

import com.caritrack.caritrack_api.item.application.service.ItemService;
import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemRequestDto;
import com.caritrack.caritrack_api.item.infraestructure.controller.dtos.ItemResponseDto;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@CrossOrigin
public class ItemController {

    private final ItemService service;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<ItemResponseDto> create(@RequestBody ItemRequestDto request) {
        return new ResponseEntity<>(service.createItem(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping("/api-id/{apiId}")
    public ResponseEntity<ItemResponseDto> getByApiId(@PathVariable String apiId) {
        return new ResponseEntity<>(service.getByApiId(apiId), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ItemResponseDto>> getByCategory(@PathVariable String category) {
        return new ResponseEntity<>(service.getByCategory(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(
                messageService.getMessage("item.delete.ok", id)
        );
    }
}
