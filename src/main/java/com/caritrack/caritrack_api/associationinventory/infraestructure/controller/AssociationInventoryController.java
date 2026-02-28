package com.caritrack.caritrack_api.associationinventory.infraestructure.controller;

import com.caritrack.caritrack_api.associationinventory.application.service.AssociationInventoryService;
import com.caritrack.caritrack_api.associationinventory.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@CrossOrigin
public class AssociationInventoryController {

    private final AssociationInventoryService service;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<AssociationInventoryResponseDto> create(@RequestBody AssociationInventoryRequestDto request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PostMapping("/upsert")
    public ResponseEntity<AssociationInventoryResponseDto> upsert(@RequestBody AssociationInventoryRequestDto request) {
        return new ResponseEntity<>(service.upsert(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AssociationInventoryResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociationInventoryResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/association/{associationId}")
    public ResponseEntity<List<AssociationInventoryResponseDto>> getByAssociation(@PathVariable Long associationId) {
        return ResponseEntity.ok(service.getByAssociation(associationId));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<AssociationInventoryResponseDto>> getByItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(service.getByItem(itemId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationInventoryResponseDto> update(
            @PathVariable Long id,
            @RequestBody AssociationInventoryRequestDto request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(messageService.getMessage("inventory.delete.ok", id));
    }

    @DeleteMapping("/association/{associationId}/item/{itemId}")
    public ResponseEntity<String> deleteByAssociationAndItem(
            @PathVariable Long associationId,
            @PathVariable Long itemId
    ) {
        service.deleteByAssociationAndItem(associationId, itemId);
        return ResponseEntity.ok(messageService.getMessage("inventory.delete.by.pair.ok", associationId, itemId));
    }
}