package com.caritrack.caritrack_api.donation.infraestructure.repository;

import com.caritrack.caritrack_api.donation.domain.DonationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "donations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", nullable = false, columnDefinition = "uuid")
    private UUID userId;

    @Column(name = "request_id", nullable = false)
    private Long requestId;

    @Column(nullable = false)
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationStatus status;
}