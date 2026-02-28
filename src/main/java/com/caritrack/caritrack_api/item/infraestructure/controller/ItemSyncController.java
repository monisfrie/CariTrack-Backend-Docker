package com.caritrack.caritrack_api.item.infraestructure.controller;

import com.caritrack.caritrack_api.item.application.service.ItemSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@CrossOrigin
public class ItemSyncController {

    private final ItemSyncService syncService;

    @PostMapping("/sync")
    public ResponseEntity<String> sync(
            @RequestParam String q,
            @RequestParam(required = false) Integer size
    ) {
        int count = syncService.syncByQuery(q, size);
        return ResponseEntity.ok("Synced items: " + count);
    }
}