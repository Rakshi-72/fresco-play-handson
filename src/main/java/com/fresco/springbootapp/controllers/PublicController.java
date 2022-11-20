package com.fresco.springbootapp.controllers;

import com.fresco.springbootapp.models.Login;
import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.security.JwtUtils;
import com.fresco.springbootapp.service.ProductService;
import com.fresco.springbootapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtUtils utils;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ProductService productService;

    @PostMapping("/login")
    public ResponseEntity<Properties> login(@RequestBody Login login) {
        Properties properties = new Properties();
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (Exception exception) {
            properties.setProperty("message", "username - password miss match");
            return new ResponseEntity<>(properties, HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
        String token = utils.generateToken(userDetails);
        properties.setProperty("token", token);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(productService.searchProducts(keyword), HttpStatus.OK);
    }

}
