package com.caritrack.caritrack_api.itemstatistics.domain;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemStatistics {

    private Long id;

    private Long associationId;
    private Long itemId;

    private Integer totalReceived;
    private Integer totalDelivered;
    private Integer totalRequested;

    private Instant lastCalculated;
}