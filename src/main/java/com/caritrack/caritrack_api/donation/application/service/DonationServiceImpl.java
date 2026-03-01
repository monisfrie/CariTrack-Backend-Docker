package com.caritrack.caritrack_api.donation.application.service;

import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventoryRepository;
import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatisticsRepository;
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

    private final AssociationInventoryRepository inventoryRepository;
    private final ItemStatisticsRepository statisticsRepository;


    @Override
    @Transactional
    public DonationResponseDto create(DonationCreateRequestDto request) {

        existingUser(request.getUserId());

        var req = requestRepository.findById(request.getRequestId())
                .orElseThrow(() -> new RequestNotFoundException(
                        messageService.getMessage("error.request.not.found", request.getRequestId())
                ));

        request.getItems().forEach(line -> existingItem(line.getItemId()));

        Long associationId = req.getAssociationId();

        Donation donation = Donation.builder()
                .userId(request.getUserId())
                .requestId(request.getRequestId())
                .date(Instant.now())
                .status(DonationStatus.PENDING)
                .build();

        Donation savedDonation = donationRepository.save(donation);

        List<DonationItem> lines = request.getItems().stream()
                .map(l -> DonationItem.builder()
                        .donationId(savedDonation.getId())
                        .itemId(l.getItemId())
                        .quantity(l.getQuantity())
                        .build())
                .toList();

        List<DonationItem> savedLines = donationItemRepository.saveAll(lines);

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
    @Transactional
    public DonationResponseDto updateStatus(Long id, DonationUpdateStatusDto request) {

        Donation donation = donationRepository.findById(id).orElseThrow(() ->
                new DonationNotFoundException(messageService.getMessage("error.donation.not.found", id))
        );

        DonationStatus previousStatus = donation.getStatus();
        DonationStatus newStatus = request.getStatus();

        // Si no cambia, devolvemos tal cual
        if (previousStatus == newStatus) {
            return toResponseWithItems(donation);
        }

        donation.setStatus(newStatus);
        Donation saved = donationRepository.save(donation);

        // ✅ Aplicar SOLO si pasa a RECEIVED por primera vez
        if (previousStatus != DonationStatus.RECEIVED && newStatus == DonationStatus.RECEIVED) {

            var req = requestRepository.findById(saved.getRequestId())
                    .orElseThrow(() -> new RequestNotFoundException(
                            messageService.getMessage("error.request.not.found", saved.getRequestId())
                    ));

            Long associationId = req.getAssociationId();

            var donationItems = donationItemRepository.findByDonationId(saved.getId());

            applyDonationToInventoryAndStats(associationId, donationItems);
        }

        return toResponseWithItems(saved);
    }


    @Override
    @Transactional
    public void delete(Long id) {
        // borrar hijos primero
        donationItemRepository.deleteByDonationId(id);
        donationRepository.deleteById(id);
    }

    private void applyDonationToInventoryAndStats(Long associationId, List<DonationItem> donationItems) {

        Instant now = Instant.now();

        for (DonationItem li : donationItems) {

            var inv = inventoryRepository.findByAssociationIdAndItemId(associationId, li.getItemId())
                    .orElse(com.caritrack.caritrack_api.associationinventory.domain.AssociationInventory.builder()
                            .associationId(associationId)
                            .itemId(li.getItemId())
                            .stockQuantity(0)
                            .minRequired(0)
                            .maxCapacity(null)
                            .lastUpdated(now)
                            .build());

            inv.setStockQuantity(inv.getStockQuantity() + li.getQuantity());
            inv.setLastUpdated(now);
            inventoryRepository.save(inv);

            var stats = statisticsRepository.findByAssociationIdAndItemId(associationId, li.getItemId())
                    .orElse(com.caritrack.caritrack_api.itemstatistics.domain.ItemStatistics.builder()
                            .associationId(associationId)
                            .itemId(li.getItemId())
                            .totalReceived(0)
                            .totalDelivered(0)
                            .totalRequested(0)
                            .lastCalculated(now)
                            .build());

            stats.setTotalReceived(stats.getTotalReceived() + li.getQuantity());
            stats.setLastCalculated(now);
            statisticsRepository.save(stats);
        }
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