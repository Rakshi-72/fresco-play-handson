package com.fresco.springbootapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fresco.springbootapp.models.Product;

public interface SellerService {
    ResponseEntity<List<Product>> getAllProducts();

    ResponseEntity<Product> getProductById(Integer id);

    ResponseEntity<Product> saveProduct(Product product);

    void updateProduct(Product product);

    Boolean deleteProduct(Integer id);
}
