package com.caritrack.caritrack_api.request.application.service;

import com.caritrack.caritrack_api.request.domain.RequestStatus;
import com.caritrack.caritrack_api.request.infraestructure.controller.dtos.*;

import java.util.List;

public interface RequestService {

    RequestResponseDto create(RequestRequestDto request);

    List<RequestResponseDto> getAll();

    RequestResponseDto getById(Long id);

    List<RequestResponseDto> getByAssociation(Long associationId);

    List<RequestResponseDto> getByStatus(RequestStatus status);

    RequestResponseDto update(Long id, RequestRequestDto request);

    RequestResponseDto updateStatus(Long id, RequestUpdateStatusDto request);

    void delete(Long id);
}