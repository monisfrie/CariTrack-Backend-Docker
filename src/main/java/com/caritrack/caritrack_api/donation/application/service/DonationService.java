package com.caritrack.caritrack_api.donation.application.service;

import com.caritrack.caritrack_api.donation.domain.DonationStatus;
import com.caritrack.caritrack_api.donation.infraestructure.controller.dtos.*;

import java.util.List;
import java.util.UUID;

public interface DonationService {

    DonationResponseDto create(DonationCreateRequestDto request);

    List<DonationResponseDto> getAll();

    DonationResponseDto getById(Long id);

    List<DonationResponseDto> getByRequest(Long requestId);

    List<DonationResponseDto> getByUser(UUID userId);

    void delete(Long id);

    List<DonationResponseDto> getByAssociation(Long associationId, DonationStatus status);

    DonationResponseDto updateStatus(Long donationId, DonationUpdateStatusDto request);

}