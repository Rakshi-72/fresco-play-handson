package com.fresco.springbootapp.controllers;

import com.fresco.springbootapp.models.User;
import com.fresco.springbootapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired private UserRepo repo;

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        repo.save(user);
        System.out.println("user saved successfully");
    }
}
