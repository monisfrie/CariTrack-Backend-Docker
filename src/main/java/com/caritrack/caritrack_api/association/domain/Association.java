package com.caritrack.caritrack_api.association.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Association {

    private Long id;
    private String name;
    private String location;
    private String description;
    private String category;
}

