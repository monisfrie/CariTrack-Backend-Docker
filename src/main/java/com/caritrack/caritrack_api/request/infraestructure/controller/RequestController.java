package com.caritrack.caritrack_api.request.infraestructure.controller;

import com.caritrack.caritrack_api.request.application.service.RequestService;
import com.caritrack.caritrack_api.request.domain.RequestStatus;
import com.caritrack.caritrack_api.request.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
@CrossOrigin
public class RequestController {

    private final RequestService service;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<RequestResponseDto> create(@RequestBody RequestRequestDto request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RequestResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/association/{associationId}")
    public ResponseEntity<List<RequestResponseDto>> getByAssociation(@PathVariable Long associationId) {
        return ResponseEntity.ok(service.getByAssociation(associationId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<RequestResponseDto>> getByStatus(@PathVariable RequestStatus status) {
        return ResponseEntity.ok(service.getByStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestResponseDto> update(
            @PathVariable Long id,
            @RequestBody RequestRequestDto request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody RequestUpdateStatusDto request
    ) {
        return ResponseEntity.ok(service.updateStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(messageService.getMessage("request.delete.ok", id));
    }
}