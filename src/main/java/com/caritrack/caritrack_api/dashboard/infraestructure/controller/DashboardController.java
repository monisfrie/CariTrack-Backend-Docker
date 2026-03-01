package com.caritrack.caritrack_api.dashboard.infraestructure.controller;

import com.caritrack.caritrack_api.dashboard.application.service.DashboardService;
import com.caritrack.caritrack_api.dashboard.infraestructure.controller.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {

    private final DashboardService service;

    @GetMapping("/association/{associationId}/low-stock")
    public ResponseEntity<List<LowStockItemDto>> lowStock(@PathVariable Long associationId) {
        return ResponseEntity.ok(service.getLowStock(associationId));
    }

    @GetMapping("/association/{associationId}/top-received")
    public ResponseEntity<List<TopReceivedItemDto>> topReceived(
            @PathVariable Long associationId,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(service.getTopReceived(associationId, limit));
    }

    @GetMapping("/association/{associationId}/summary")
    public ResponseEntity<AssociationSummaryDto> summary(@PathVariable Long associationId) {
        return ResponseEntity.ok(service.getSummary(associationId));
    }
}