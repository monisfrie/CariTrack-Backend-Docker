package com.caritrack.caritrack_api.request.infraestructure.repository;

import com.caritrack.caritrack_api.request.domain.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "association_id", nullable = false)
    private Long associationId;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String summary;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;
}