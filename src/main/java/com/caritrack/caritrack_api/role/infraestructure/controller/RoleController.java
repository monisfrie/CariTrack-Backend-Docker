package com.caritrack.caritrack_api.role.infraestructure.controller;

import com.caritrack.caritrack_api.role.application.service.RoleService;
import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleRequestDto;
import com.caritrack.caritrack_api.role.infraestructure.controller.dtos.RoleResponseDto;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@CrossOrigin
public class RoleController {

    private final RoleService roleService;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<RoleResponseDto> create(@RequestBody RoleRequestDto request) {
        return new ResponseEntity<>(roleService.createRole(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getAll() {
        return new ResponseEntity<>(roleService.getAllRoles(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(roleService.getById(id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok(messageService.getMessage(
                "role.delete.ok",
                id));
    }
}

