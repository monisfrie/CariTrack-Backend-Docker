package com.caritrack.caritrack_api.itemstatistics.infraestructure.repository;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@Table(
        name = "item_statistics",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"association_id", "item_id"})
        }
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemStatisticsJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "association_id", nullable = false)
    private Long associationId;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "total_received", nullable = false)
    private Integer totalReceived;

    @Column(name = "total_delivered", nullable = false)
    private Integer totalDelivered;

    @Column(name = "total_requested", nullable = false)
    private Integer totalRequested;

    @Column(name = "last_calculated", nullable = false)
    private Instant lastCalculated;
}