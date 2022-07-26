package com.korolyovegor.onlineStoreBackend.repository;

import com.korolyovegor.onlineStoreBackend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    default User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user authenticated"));
        return user;
    }
}