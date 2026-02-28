package com.caritrack.caritrack_api.external.foodapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffSearchResponseDto {
    private List<OffProductDto> products;
}