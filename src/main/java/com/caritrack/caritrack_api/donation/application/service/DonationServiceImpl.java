package com.caritrack.caritrack_api.donation.application.service;

import com.caritrack.caritrack_api.request.domain.RequestRepository;
import com.caritrack.caritrack_api.request.utils.exceptions.RequestNotFoundException;
import com.caritrack.caritrack_api.item.domain.ItemRepository;
import com.caritrack.caritrack_api.item.utils.exceptions.ItemNotFoundException;
import com.caritrack.caritrack_api.user.domain.UserDao;
import com.caritrack.caritrack_api.user.utils.exceptions.UserNotFoundException;

import com.caritrack.caritrack_api.donation.application.mapper.DonationMapperService;
import com.caritrack.caritrack_api.donation.domain.*;
import com.caritrack.caritrack_api.donation.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.donation.utils.exceptions.DonationNotFoundException;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final DonationItemRepository donationItemRepository;

    private final UserDao userDao;
    private final RequestRepository requestRepository;
    private final ItemRepository itemRepository;

    private final DonationMapperService mapper;
    private final MessageService messageService;

    @Override
    @Transactional
    public DonationResponseDto create(DonationCreateRequestDto request) {

        // Validaciones existencia
        existingUser(request.getUserId());
        existingRequest(request.getRequestId());
        request.getItems().forEach(line -> existingItem(line.getItemId()));

        // Crear Donation
        Donation donation = Donation.builder()
                .userId(request.getUserId())
                .requestId(request.getRequestId())
                .date(Instant.now())
                .status(DonationStatus.PENDING)
                .build();

        Donation savedDonation = donationRepository.save(donation);

        // Crear DonationItems
        List<DonationItem> lines = request.getItems().stream()
                .map(l -> DonationItem.builder()
                        .donationId(savedDonation.getId())
                        .itemId(l.getItemId())
                        .quantity(l.getQuantity())
                        .build())
                .toList();

        List<DonationItem> savedLines = donationItemRepository.saveAll(lines);

        // Montar respuesta
        DonationResponseDto response = mapper.toResponseDto(savedDonation);
        response.setItems(savedLines.stream()
                .map(li -> new DonationItemResponseDto(li.getId(), li.getItemId(), li.getQuantity()))
                .toList());

        return response;
    }

    @Override
    public List<DonationResponseDto> getAll() {
        return donationRepository.findAll().stream()
                .map(this::toResponseWithItems)
                .toList();
    }

    @Override
    public DonationResponseDto getById(Long id) {
        Donation donation = donationRepository.findById(id).orElseThrow(() ->
                new DonationNotFoundException(messageService.getMessage("error.donation.not.found", id))
        );
        return toResponseWithItems(donation);
    }

    @Override
    public List<DonationResponseDto> getByRequest(Long requestId) {
        existingRequest(requestId);
        return donationRepository.findByRequestId(requestId).stream()
                .map(this::toResponseWithItems)
                .toList();
    }

    @Override
    public List<DonationResponseDto> getByUser(UUID userId) {
        existingUser(userId);
        return donationRepository.findByUserId(userId).stream()
                .map(this::toResponseWithItems)
                .toList();
    }

    @Override
    public DonationResponseDto updateStatus(Long id, DonationUpdateStatusDto request) {
        Donation donation = donationRepository.findById(id).orElseThrow(() ->
                new DonationNotFoundException(messageService.getMessage("error.donation.not.found", id))
        );
        donation.setStatus(request.getStatus());
        Donation saved = donationRepository.save(donation);
        return toResponseWithItems(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // borrar hijos primero
        donationItemRepository.deleteByDonationId(id);
        donationRepository.deleteById(id);
    }

    private DonationResponseDto toResponseWithItems(Donation donation) {
        DonationResponseDto response = mapper.toResponseDto(donation);
        var items = donationItemRepository.findByDonationId(donation.getId());
        response.setItems(items.stream()
                .map(li -> new DonationItemResponseDto(li.getId(), li.getItemId(), li.getQuantity()))
                .toList());
        return response;
    }

    private void existingUser(UUID userId) {
        userDao.findById(userId).orElseThrow(() ->
                new UserNotFoundException(messageService.getMessage("error.not.found.user", userId))
        );
    }

    private void existingRequest(Long requestId) {
        requestRepository.findById(requestId).orElseThrow(() ->
                new RequestNotFoundException(messageService.getMessage("error.request.not.found", requestId))
        );
    }

    private void existingItem(Long itemId) {
        itemRepository.findById(itemId).orElseThrow(() ->
                new ItemNotFoundException(messageService.getMessage("error.item.not.found", itemId))
        );
    }
}