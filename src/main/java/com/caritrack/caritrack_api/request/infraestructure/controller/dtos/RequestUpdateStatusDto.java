package com.caritrack.caritrack_api.request.infraestructure.controller.dtos;

import com.caritrack.caritrack_api.request.domain.RequestStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateStatusDto {

    @NotNull(message = "{error.blank.request.status}")
    private RequestStatus status;
}