package com.korolyovegor.onlineStoreBackend.controller;

import com.korolyovegor.onlineStoreBackend.model.security.User;
import com.korolyovegor.onlineStoreBackend.service.impl.JwtUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class UserController {
    private final JwtUserDetailsService userService;

    @Resource(name="authenticationManager")
    private AuthenticationManager authManager;

    @Autowired
    public UserController(JwtUserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/logout")
    public void logOut() {
        SecurityContextHolder.clearContext();
    }

    @PostMapping(value = "/login")
    public void login(HttpServletRequest request,
                      @RequestParam("username") final String username,
                      @RequestParam("password") final String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            log.error(e.getMessage());
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/registration")
    public void registration(HttpServletRequest request, @RequestBody User user) {
        userService.register(user);
        login(request, user.getUsername(), user.getPassword());
    }

    @GetMapping("/existsUser/{username}")
    public boolean findUserById(@PathVariable String username) {
        return userService.findByUsername(username).isPresent();
    }
}
