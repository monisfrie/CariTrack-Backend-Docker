package com.caritrack.caritrack_api.category.infraestructure.repository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}