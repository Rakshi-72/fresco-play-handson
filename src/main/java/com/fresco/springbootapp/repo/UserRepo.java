package com.fresco.springbootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.springbootapp.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
