package com.caritrack.caritrack_api.user.domain;

import java.util.*;

public interface UserDao {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void deleteById(UUID id);


}
