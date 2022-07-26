package com.korolyovegor.onlineStoreBackend.service;

import com.korolyovegor.onlineStoreBackend.model.security.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);

    List<User> getAll();

    Optional<User> findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    boolean isUsernameFree(String username);

    PasswordEncoder getEncoder();
}
