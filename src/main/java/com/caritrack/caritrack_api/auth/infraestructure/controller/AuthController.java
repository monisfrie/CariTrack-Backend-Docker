package com.caritrack.caritrack_api.auth.infraestructure.controller;

import com.caritrack.caritrack_api.auth.application.service.AuthService;
import com.caritrack.caritrack_api.auth.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody AuthRegisterRequestDto request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody AuthLoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}