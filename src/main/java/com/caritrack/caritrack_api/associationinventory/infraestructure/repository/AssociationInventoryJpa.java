package com.caritrack.caritrack_api.associationinventory.infraestructure.repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@Table(
        name = "association_inventory",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"association_id", "item_id"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociationInventoryJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "association_id", nullable = false)
    private Long associationId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "min_required")
    private Integer minRequired;

    @Column(name = "max_capacity")
    private Integer maxCapacity;

    @Column(name = "last_updated", nullable = false)
    private Instant lastUpdated;
}