package com.caritrack.caritrack_api.auth.application.service;

import com.caritrack.caritrack_api.auth.infraestructure.controller.dtos.*;
import com.caritrack.caritrack_api.shared.dynamicMessages.MessageService;
import com.caritrack.caritrack_api.user.application.mapper.UserServiceMapper;
import com.caritrack.caritrack_api.user.domain.UserDao;
import com.caritrack.caritrack_api.user.utils.exceptions.UserNotFoundException;
import com.caritrack.caritrack_api.user.utils.exceptions.UserAlreadyExistsException;
import com.caritrack.caritrack_api.user.infraestructure.controller.dtos.UserResponseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDao userDao;
    private final UserServiceMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;

    @Override
    public UserResponseDto register(AuthRegisterRequestDto request) {

        if (userDao.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    messageService.getMessage("error.user.email.exists", request.getEmail())
            );
        }

        var domain = mapper.registerToDomain(request);
        domain.updatePasswordHash(passwordEncoder.encode(request.getPassword()));

        var saved = userDao.save(domain);
        return mapper.toResponse(saved);
    }


    @Override
    public UserResponseDto login(AuthLoginRequestDto request) {
        existingEmail(request.getEmail());
        var user = userDao.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        messageService.getMessage("error.user.login.invalid")
                ));

        boolean ok = passwordEncoder.matches(request.getPassword(), user.getPasswordHash());

        if (!ok) {
            throw new UserNotFoundException(messageService.getMessage("error.user.login.invalid"));
        }

        return mapper.toResponse(user);
    }

    private void existingEmail(@Email @NotBlank String email) {
        userDao.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(messageService.getMessage(
                        "error.not.found.by.email",
                        email
                )));

    }
}