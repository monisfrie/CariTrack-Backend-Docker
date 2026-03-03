package com.caritrack.caritrack_api.donation.domain;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Donation {

    private Long id;
    private UUID userId;     // donante
    private Long requestId;  // request a la que responde
    private Instant date;
    private DonationStatus status;
}