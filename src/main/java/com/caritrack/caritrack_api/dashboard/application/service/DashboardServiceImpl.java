package com.caritrack.caritrack_api.dashboard.application.service;

import com.caritrack.caritrack_api.association.domain.AssociationRepository;
import com.caritrack.caritrack_api.association.utils.exceptions.AssociationNotFoundException;
import com.caritrack.caritrack_api.associationinventory.domain.AssociationInventoryRepository;
import com.caritrack.caritrack_api.dashboard.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.item.domain.ItemRepository;
import com.caritrack.caritrack_api.itemstatistics.domain.ItemStatisticsRepository;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final AssociationRepository associationRepository;
    private final AssociationInventoryRepository inventoryRepository;
    private final ItemStatisticsRepository statisticsRepository;
    private final MessageService messageService;
    private final ItemRepository itemRepository;

    @Override
    public List<LowStockItemDto> getLowStock(Long associationId) {
        existingAssociation(associationId);

        return inventoryRepository.findByAssociationId(associationId).stream()
                .filter(inv -> inv.getMinRequired() != null)
                .filter(inv -> inv.getStockQuantity() < inv.getMinRequired())
                .map(inv -> {
                    var item = itemRepository.findById(inv.getItemId()).orElse(null);

                    return new LowStockItemDto(
                            inv.getItemId(),
                            item != null ? item.getName() : "Unknown item",
                            item != null ? item.getImageUrl() : null,
                            inv.getStockQuantity(),
                            inv.getMinRequired()
                    );
                })
                .toList();
    }

    @Override
    public List<TopReceivedItemDto> getTopReceived(Long associationId, int limit) {
        existingAssociation(associationId);

        int lim = (limit <= 0) ? 10 : limit;

        return statisticsRepository.findByAssociationId(associationId).stream()
                .sorted(Comparator.comparingInt(s -> -s.getTotalReceived()))
                .limit(lim)
                .map(s -> {
                    var item = itemRepository.findById(s.getItemId()).orElse(null);

                    return new TopReceivedItemDto(
                            s.getItemId(),
                            item != null ? item.getName() : "Unknown item",
                            item != null ? item.getImageUrl() : null,
                            s.getTotalReceived()
                    );
                })
                .toList();
    }

    @Override
    public AssociationSummaryDto getSummary(Long associationId) {
        existingAssociation(associationId);

        var inventory = inventoryRepository.findByAssociationId(associationId);

        int totalDifferentItems = inventory.size();
        int totalStock = inventory.stream()
                .mapToInt(i -> i.getStockQuantity() == null ? 0 : i.getStockQuantity())
                .sum();

        return new AssociationSummaryDto(associationId, totalDifferentItems, totalStock);
    }

    private void existingAssociation(Long associationId) {
        associationRepository.findById(associationId)
                .orElseThrow(() -> new AssociationNotFoundException(
                        messageService.getMessage("error.association.not.found", associationId)
                ));
    }
}