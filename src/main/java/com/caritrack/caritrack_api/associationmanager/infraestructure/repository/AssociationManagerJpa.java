package com.caritrack.caritrack_api.associationmanager.infraestructure.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "association_managers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "association_id"})
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociationManagerJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "association_id", nullable = false)
    private Long associationId;
}
