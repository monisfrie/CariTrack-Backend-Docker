package com.caritrack.caritrack_api.associationmanager.infraestructure.controller;

import com.caritrack.caritrack_api.associationmanager.application.service.AssociationManagerService;
import com.caritrack.caritrack_api.associationmanager.infraestructure.controller.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/association-managers")
@RequiredArgsConstructor
@CrossOrigin
public class AssociationManagerController {

    private final AssociationManagerService service;

    @PostMapping
    public ResponseEntity<AssociationManagerResponseDto> create(
            @RequestBody AssociationManagerRequestDto request) {

        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssociationManagerResponseDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/association/{associationId}")
    public ResponseEntity<List<AssociationManagerResponseDto>> getByAssociation(
            @PathVariable Long associationId) {

        return ResponseEntity.ok(service.getByAssociation(associationId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AssociationManagerResponseDto>> getByUser(
            @PathVariable UUID userId) {

        return ResponseEntity.ok(service.getByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}