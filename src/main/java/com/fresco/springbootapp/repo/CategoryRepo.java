package com.fresco.springbootapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.springbootapp.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
