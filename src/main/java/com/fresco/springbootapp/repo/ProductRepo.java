package com.fresco.springbootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.springbootapp.models.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
