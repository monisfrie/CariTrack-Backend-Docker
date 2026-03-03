package com.caritrack.caritrack_api.request.infraestructure.controller.dtos;

import com.caritrack.caritrack_api.request.domain.RequestStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponseDto {

    private Long id;
    private Long associationId;

    private String title;
    private String summary;
    private String description;

    private RequestStatus status;
}