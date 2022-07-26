package com.korolyovegor.onlineStoreBackend.model.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {

        List<GrantedAuthority> authorities = rolesMapToGrantedAuthority(new ArrayList<>(user.getRoles()));

        log.info("user with username " + user.getUsername() + " was loaded with authorities:" + authorities);

        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(UserStatusType.ACTIVE),
                user.getUpdated(),
                authorities
        );
    }

    private static List<GrantedAuthority> rolesMapToGrantedAuthority(ArrayList<Role> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toList());
        return authorities;
    }
}