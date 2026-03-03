package com.caritrack.caritrack_api.item.infraestructure.repository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(
        name = "items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"api_source","api_id"})
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "api_id", nullable = false, unique = true)
    private String apiId;

    @Column(name="api_source", nullable=false)
    private String apiSource; // "openfoodfacts"

    @Column(nullable = false)
    private String name;

    @Column(length = 1500)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    // versión simple
    private String category;
}