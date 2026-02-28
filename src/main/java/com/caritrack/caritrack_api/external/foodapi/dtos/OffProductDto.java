package com.caritrack.caritrack_api.external.foodapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OffProductDto {

    @JsonProperty("code")
    private String code; // apiId

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("categories")
    private String categories; // texto con categorías
}