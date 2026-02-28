package com.caritrack.caritrack_api.request.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {

    private Long id;
    private Long associationId;

    private String title;
    private String summary;
    private String description;

    private RequestStatus status;
}