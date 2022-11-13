package com.fresco.springbootapp.controllers;

import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = service.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<User> loadUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User loadUser(@PathVariable Integer id) {
        return service.findById(id);
    }
}
