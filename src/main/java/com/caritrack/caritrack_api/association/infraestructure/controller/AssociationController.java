package com.caritrack.caritrack_api.association.infraestructure.controller;

import com.caritrack.caritrack_api.association.application.service.AssociationService;
import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationRequestDto;
import com.caritrack.caritrack_api.association.infraestructure.controller.dtos.AssociationResponseDto;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/associations")
@RequiredArgsConstructor
@CrossOrigin
public class AssociationController {

    private final AssociationService associationService;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<AssociationResponseDto> create(@RequestBody AssociationRequestDto request) {
        return new ResponseEntity<>(associationService.createAssociation(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssociationResponseDto>> getAll() {
        return new ResponseEntity<>(associationService.getAllAssociations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociationResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(associationService.getById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        associationService.deleteAssociation(id);
        return ResponseEntity.ok(messageService.getMessage(
                "association.delete.ok",
                id
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociationResponseDto> update(
            @PathVariable Long id,
            @RequestBody AssociationRequestDto request) {

        return new ResponseEntity<>(
                associationService.updateAssociation(id, request),
                HttpStatus.OK
        );
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<AssociationResponseDto>> getByCategory(
            @PathVariable String category) {

        return new ResponseEntity<>(
                associationService.getByCategory(category),
                HttpStatus.OK
        );
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        return ResponseEntity.ok(
                associationService.existsById(id)
        );
    }



}

