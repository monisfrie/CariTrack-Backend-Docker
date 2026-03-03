package com.caritrack.caritrack_api.itemstatistics.infraestructure.controller.dtos;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemStatisticsResponseDto {

    private Long id;
    private Long associationId;
    private Long itemId;

    private Integer totalReceived;
    private Integer totalDelivered;
    private Integer totalRequested;

    private Instant lastCalculated;
}