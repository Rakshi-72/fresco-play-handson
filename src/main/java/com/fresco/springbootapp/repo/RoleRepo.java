package com.fresco.springbootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.springbootapp.models.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
