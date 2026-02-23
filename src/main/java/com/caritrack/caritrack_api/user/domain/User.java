package com.caritrack.caritrack_api.user.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {

    private UUID id;
    private Role role;
    private String username;
    private String name;
    private String email;
    private String passwordHash;
    private boolean active = true;

    public void updatePasswordHash(String newHash) {
        this.passwordHash = newHash;
    }

}
