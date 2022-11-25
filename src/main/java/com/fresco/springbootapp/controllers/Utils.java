package com.fresco.springbootapp.controllers;

import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Utils {
    private UserRepo repo;

    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String username = details.getUsername();
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user with the username not found"));
    }
}
