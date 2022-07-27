package com.korolyovegor.onlineStoreBackend.service.impl;

import com.korolyovegor.onlineStoreBackend.model.security.*;
import com.korolyovegor.onlineStoreBackend.repository.RoleRepository;
import com.korolyovegor.onlineStoreBackend.repository.UserRepository;
import com.korolyovegor.onlineStoreBackend.security.LoginAttemptService;
import com.korolyovegor.onlineStoreBackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class JwtUserDetailsService implements UserService, UserDetailsService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final LoginAttemptService loginAttemptService;
    private final HttpServletRequest request;

    public JwtUserDetailsService(@Autowired RoleRepository roleRepository,
                                 @Autowired UserRepository userRepository,
                                 @Autowired PasswordEncoder encoder,
                                 @Autowired LoginAttemptService loginAttemptService,
                                 @Autowired HttpServletRequest request) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.loginAttemptService = loginAttemptService;
        this.request = request;
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = getClientIP();
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }

        User user = findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User with username: " + username + " not found"));
//        User user = userService.findByUsername(username).get();

        JwtUser jwtUser = JwtUserFactory.create(user);
//        System.out.println(jwtUser);
        return jwtUser;
    }

    public PasswordEncoder getEncoder() {
        return encoder;
    }

    @Override
    public User register(User user) {
        Role role = roleRepository.findByName(RoleNameType.ROLE_USER);
        List<Role> userRoles = List.of(role);

        user.setRoles(userRoles);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(UserStatusType.ACTIVE);

        User savedUser = userRepository.save(user);
        log.info("user with username " + savedUser.getUsername() + " has been registered");
        return savedUser;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public boolean isUsernameFree(String username) {
        return findByUsername(username).isEmpty();
    }

}