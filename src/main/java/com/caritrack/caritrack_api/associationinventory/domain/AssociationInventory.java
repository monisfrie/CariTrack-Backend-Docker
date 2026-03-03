package com.caritrack.caritrack_api.associationinventory.domain;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociationInventory {

    private Long id;
    private Long associationId;
    private Long itemId;

    private Integer stockQuantity;
    private Integer minRequired;
    private Integer maxCapacity;

    private Instant lastUpdated;
}