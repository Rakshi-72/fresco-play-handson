package com.fresco.springbootapp.repo;

import com.fresco.springbootapp.models.Product;
import com.fresco.springbootapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Integer> {

    List<Product> findByProductNameIgnoreCaseContaining(String key);

    Optional<List<Product>> findBySeller(User user);
}
