package com.caritrack.caritrack_api.itemstatistics.infraestructure.controller.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemStatisticsUpsertDto {

    @NotNull(message = "{error.blank.stats.associationId}")
    private Long associationId;

    @NotNull(message = "{error.blank.stats.itemId}")
    private Long itemId;

    @Min(value = 0, message = "{error.min.stats.totalReceived}")
    private Integer totalReceived;

    @Min(value = 0, message = "{error.min.stats.totalDelivered}")
    private Integer totalDelivered;

    @Min(value = 0, message = "{error.min.stats.totalRequested}")
    private Integer totalRequested;
}