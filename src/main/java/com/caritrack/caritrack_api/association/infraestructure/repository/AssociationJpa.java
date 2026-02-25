package com.caritrack.caritrack_api.association.infraestructure.repository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "associations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociationJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;

    @Column(length = 1000)
    private String description;

    private String category;
}

