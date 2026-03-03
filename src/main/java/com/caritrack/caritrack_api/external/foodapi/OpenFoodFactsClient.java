package com.caritrack.caritrack_api.external.foodapi;

import com.caritrack.caritrack_api.external.foodapi.dtos.OffSearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class OpenFoodFactsClient {

    private final RestClient restClient;

    @Value("${foodapi.base-url}")
    private String baseUrl;

    public OffSearchResponseDto search(String query, int pageSize) {

        String uri = UriComponentsBuilder
                .fromHttpUrl(baseUrl)
                .path("/api/v2/search")
                .queryParam("search_terms", query)
                .queryParam("page_size", pageSize)
                .queryParam("fields", "code,product_name,image_url,categories")
                .build()
                .toUriString();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(OffSearchResponseDto.class);
    }
}