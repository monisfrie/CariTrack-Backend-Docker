package com.caritrack.caritrack_api.associationinventory.infraestructure.controller.dtos;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationInventoryResponseDto {

    private Long id;
    private Long associationId;
    private Long itemId;

    private Integer stockQuantity;
    private Integer minRequired;
    private Integer maxCapacity;

    private Instant lastUpdated;
}