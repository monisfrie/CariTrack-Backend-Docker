package com.caritrack.caritrack_api.category.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private Long id;
    private String name;
}