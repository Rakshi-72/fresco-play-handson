package com.fresco.springbootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.springbootapp.models.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
