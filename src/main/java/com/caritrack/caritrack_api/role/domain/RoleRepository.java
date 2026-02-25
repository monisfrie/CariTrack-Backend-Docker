package com.caritrack.caritrack_api.role.domain;


import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Role save (Role role);

    Optional<Role> findById (Long id);

    List<Role> findAll ();

    void deleteById (Long id);
}
