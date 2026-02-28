package com.caritrack.caritrack_api.item.application.service;

import com.caritrack.caritrack_api.external.foodapi.OpenFoodFactsClient;
import com.caritrack.caritrack_api.external.foodapi.dtos.OffProductDto;
import com.caritrack.caritrack_api.external.foodapi.dtos.OffSearchResponseDto;
import com.caritrack.caritrack_api.item.domain.Item;
import com.caritrack.caritrack_api.item.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSyncService {

    private final OpenFoodFactsClient client;
    private final ItemRepository repository;

    @Value("${foodapi.source:openfoodfacts}")
    private String apiSource;

    @Value("${foodapi.default-page-size:50}")
    private int defaultPageSize;

    public int syncByQuery(String query, Integer pageSize) {

        int size = (pageSize == null || pageSize <= 0) ? defaultPageSize : pageSize;

        OffSearchResponseDto response = client.search(query, size);
        if (response == null || response.getProducts() == null) return 0;

        List<OffProductDto> products = response.getProducts();

        products.forEach(p -> {
            if (p.getCode() == null || p.getProductName() == null) return;

            Item item = repository.findByApiId(p.getCode())
                    .orElse(Item.builder().apiId(p.getCode()).build());

            item.setApiSource(apiSource);

            item.setName(p.getProductName());
            item.setImageUrl(p.getImageUrl());
            item.setCategory(p.getCategories());

            repository.save(item);
        });

        return products.size();
    }
}