package com.caritrack.caritrack_api.itemstatistics.infraestructure.controller;

import com.caritrack.caritrack_api.itemstatistics.application.service.ItemStatisticsService;
import com.caritrack.caritrack_api.itemstatistics.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin
public class ItemStatisticsController {

    private final ItemStatisticsService service;
    private final MessageService messageService;

    @PostMapping("/upsert")
    public ResponseEntity<ItemStatisticsResponseDto> upsert(@RequestBody ItemStatisticsUpsertDto request) {
        return new ResponseEntity<>(service.upsert(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemStatisticsResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemStatisticsResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/association/{associationId}")
    public ResponseEntity<List<ItemStatisticsResponseDto>> getByAssociation(@PathVariable Long associationId) {
        return ResponseEntity.ok(service.getByAssociation(associationId));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<ItemStatisticsResponseDto>> getByItem(@PathVariable Long itemId) {
        return ResponseEntity.ok(service.getByItem(itemId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(messageService.getMessage("stats.delete.ok", id));
    }

    @DeleteMapping("/association/{associationId}/item/{itemId}")
    public ResponseEntity<String> deleteByAssociationAndItem(
            @PathVariable Long associationId,
            @PathVariable Long itemId
    ) {
        service.deleteByAssociationAndItem(associationId, itemId);
        return ResponseEntity.ok(
                messageService.getMessage("stats.delete.by.pair.ok", associationId, itemId)
        );
    }
}