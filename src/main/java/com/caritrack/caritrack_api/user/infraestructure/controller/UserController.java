package com.caritrack.caritrack_api.user.infraestructure.controller;

import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import com.caritrack.caritrack_api.user.application.service.UserService;
import com.caritrack.caritrack_api.user.domain.User;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.UserRequestDto;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.UserResponseDto;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserRequestDto request) {

        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable UUID id) {

        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {

        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        userService.delete(id);
        return ResponseEntity.ok(messageService.getMessage(
                "message.delete.ok",
                id
        ));
    }
}