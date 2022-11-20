package com.fresco.springbootapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.UserRepo;

@Component
public class Utils {
    @Autowired
    private UserRepo repo;

    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String username = details.getUsername();
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user with the username not found"));
    }
}
