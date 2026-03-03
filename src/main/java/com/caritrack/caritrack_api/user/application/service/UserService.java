package com.caritrack.caritrack_api.user.application.service;

import com.caritrack.caritrack_api.user.domain.User;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.*;

import java.util.*;

import java.util.UUID;

public interface UserService {

    UserResponseDto create(UserRequestDto request);

    UserResponseDto getById(UUID id);

    List<UserResponseDto> getAll();

    void delete(UUID id);
}
