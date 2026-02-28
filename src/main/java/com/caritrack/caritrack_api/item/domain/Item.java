package com.caritrack.caritrack_api.item.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    private Long id;
    private String apiId;
    private String apiSource;
    private String name;
    private String description;
    private String imageUrl;

    private String category;
}
