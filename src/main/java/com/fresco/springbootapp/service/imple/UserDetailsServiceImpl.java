package com.fresco.springbootapp.service.imple;

import com.fresco.springbootapp.models.CustomUserDeatils;
import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("user nt found"));
        return new CustomUserDeatils(user);
    }
}
