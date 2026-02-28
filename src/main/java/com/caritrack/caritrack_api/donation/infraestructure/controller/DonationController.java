package com.caritrack.caritrack_api.donation.infraestructure.controller;

import com.caritrack.caritrack_api.donation.application.service.DonationService;
import com.caritrack.caritrack_api.donation.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
@CrossOrigin
public class DonationController {

    private final DonationService service;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<DonationResponseDto> create(@RequestBody DonationCreateRequestDto request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DonationResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/request/{requestId}")
    public ResponseEntity<List<DonationResponseDto>> getByRequest(@PathVariable Long requestId) {
        return ResponseEntity.ok(service.getByRequest(requestId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DonationResponseDto>> getByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getByUser(userId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<DonationResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestBody DonationUpdateStatusDto request
    ) {
        return ResponseEntity.ok(service.updateStatus(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(messageService.getMessage("donation.delete.ok", id));
    }
}