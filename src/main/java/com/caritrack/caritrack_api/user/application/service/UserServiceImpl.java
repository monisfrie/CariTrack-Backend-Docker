package com.caritrack.caritrack_api.user.application.service;

import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import com.caritrack.caritrack_api.user.application.mapper.UserServiceMapper;
import com.caritrack.caritrack_api.user.domain.*;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.user.utils.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao repository;
    private final UserServiceMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;

    @Override
    public UserResponseDto create(UserRequestDto request) {

        var domain = mapper.requestToDomain(request);

        domain.updatePasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        var saved = repository.save(domain);

        return mapper.toResponse(saved);
    }

    @Override
    public UserResponseDto getById(UUID id) {

        var user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(messageService.getMessage(
                        "error.not.found.user",
                        id
                )));

        return mapper.toResponse(user);
    }

    @Override
    public List<UserResponseDto> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void delete(UUID id) {

        if (!repository.findById(id).isPresent()) {
            throw new UserNotFoundException(messageService.getMessage(
                    "error.not.found.user",
                    id
            ));
        }

        repository.deleteById(id);
    }
}

