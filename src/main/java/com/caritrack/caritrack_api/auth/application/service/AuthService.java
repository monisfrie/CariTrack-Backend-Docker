package com.caritrack.caritrack_api.auth.application.service;

import com.caritrack.caritrack_api.auth.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.UserResponseDto;

public interface AuthService {
    UserResponseDto register(AuthRegisterRequestDto request);
    UserResponseDto login(AuthLoginRequestDto request);
}