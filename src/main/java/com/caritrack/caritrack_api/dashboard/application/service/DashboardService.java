package com.caritrack.caritrack_api.dashboard.application.service;

import com.caritrack.caritrack_api.dashboard.infraestructure.controller.dtos.*;

import java.util.List;

public interface DashboardService {

    List<LowStockItemDto> getLowStock(Long associationId);

    List<TopReceivedItemDto> getTopReceived(Long associationId, int limit);

    AssociationSummaryDto getSummary(Long associationId);
}